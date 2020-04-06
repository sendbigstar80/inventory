package com.logistics.cloud.aspect;

import com.alibaba.fastjson.JSON;
import com.logistics.cloud.annotation.OperationLogDetail;
import com.logistics.cloud.aspect.model.OperationLogModel;
import com.logistics.cloud.feign.log.LogFeign;
import com.logistics.cloud.response.JsonResponse;
import com.logistics.cloud.tools.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 在方法上增加@Aspect 注解声明切面，使用@Pointcut注解定义切点，标记方法。
 * 使用切点增加的时机注解：@Before,@Around,@AfterReturning,@AfterThrowing,@After
 */

@Aspect
@Component
@Slf4j
public class LogAspect {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    private LogFeign logFeign;

    /**
     * 此处的切点是注解的方式，也可以使用包名的方式达到相同的效果
     */
    @Pointcut("@annotation(com.logistics.cloud.annotation.OperationLogDetail)")
    public void operationLogModel() {
    }

    /**
     * 环绕增强,相当于MethodInterceptor
     */
    @Around("operationLogModel()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        long time = System.currentTimeMillis();
        try {
            //获取请求对象
            obj = joinPoint.proceed();
            log.info("获取请求对象：{}", JSON.toJSONString(obj));
            //获取时间
            time = System.currentTimeMillis() - time;
            return obj;
        } finally {
            try {
                //不管是否异常也要进行添加日志
                addOperationLogModel(joinPoint, obj, time);
            } catch (Exception e) {

            }
        }
    }

    private void addOperationLogModel(ProceedingJoinPoint joinPoint, Object obj, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        OperationLogModel model = new OperationLogModel();
        //设置client的ip
        model.setRequestIp(RequestInfo.getRequestInfo(request));
        //设置client的port
        model.setRequestPort(request.getRemotePort() + "");
        //设置协议
        model.setRequestScheme(request.getScheme());
        //设置client请求路径
        model.setRequestUrl(request.getScheme() + "://" + request.getLocalName() + ":" + request.getLocalPort() + request.getRequestURI());
        //设置运行时间
        model.setRunTime(time);
        //设置返回值
        model.setReturnValue(JSON.toJSONString(obj));
        //设置请求参数
        model.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        //设置请求时间
        model.setCreateTime(sdf.format(new Date()));
        //设置请求方法
        model.setMethod(signature.getDeclaringTypeName());
        //设置用户id
        model.setUserId(1l);
        //设置用户名字
        model.setUserName("admin");
        //traceId
        model.setTraceId(MDC.get("X-B3-TraceId"));
        //获取需要注解类
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        if (null != annotation) {
            //设置日志描述
            model.setLogLevel(annotation.operationLogRank().getLevel());
            //设置接口类型
            model.setOperationType(annotation.operationType().getType());
            //操作人身份
            model.setOperationStatus(annotation.operationStatus().getStatus());
            //设置日志描述
            model.setLogDescribe(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs(), annotation));
        }
        System.out.println("记录日志：" + JSON.toJSONString(model));
        JsonResponse jsonResponse = logFeign.insertObject(model);
        if (!"200".equals(jsonResponse.getCode())){
            log.info("日志保存失败：" + JSON.toJSONString(model));
        }
    }

    /**
     * 对当前登录用户和占位符处理
     *
     * @param argNames   方法参数名称数组
     * @param args       方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    public String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation) {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < argNames.length; i++) {
            log.info("方法参数名称: {} , 方法参数：{}", argNames[i], args[i]);
            map.put(argNames[i], args[i]);
        }
        //获取注解的日志描述
        String detail = annotation.detail();
        try {
            detail = "'" + "#{currentUserName}" + "'=》" + annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                log.info("key: {} value: {}", JSON.toJSONString(key), JSON.toJSONString(value));
                detail = detail.replace("{{" + key + "}}", JSON.toJSONString(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    @Before("operationLogModel()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("进入方法前执行.....");

    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "operationLogModel()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLogModel()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLogModel()")
    public void after(JoinPoint jp) {
        System.out.println("方法最后执行.....");
    }
}

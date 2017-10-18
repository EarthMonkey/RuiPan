package util;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import dao.LogDao;
import model.Logentity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vo.UserVO;


/**
 * Created by ldchao on 2017/10/18.
 */
@Aspect
public class LogAopAction {
    @Autowired
    LogDao logDao;

    //配置接入点,如果不知道怎么配置,可以百度一下规则
    @Pointcut("execution(* controller.*.* (..))")
    private void controllerAspect(){}//定义一个切入点

    private static Log logger = LogFactory.getLog(LogAopAction.class);

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //常见日志实体对象
        Logentity log = new Logentity();
        //获取登录用户账户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //获取系统ip,这里用的是我自己的工具类,可自行网上查询获取ip方法
        String ip = GetClientMessageUtils.getIpAddr(request);
        log.setIp(ip);
        
        log.setTime(new Timestamp(System.currentTimeMillis()));

        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            logger.error(e1);
        } catch (SecurityException e1) {
            logger.error(e1);
        }
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {

                UserVO userVO = (UserVO) request.getSession().getAttribute("User");
                if(userVO==null){
                    // TODO: 2017/10/18
                }else{
                    log.setUsername(userVO.getUsername());
                }

                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setMethod(systemlog.methods());
                try {
                    object = pjp.proceed();

                    long end = System.currentTimeMillis();
                    //将计算好的时间保存在实体中
                    log.setResponsetime((int)(end-start));
                    log.setCommite("执行成功！");
                    //保存进数据库
                    logDao.saveAndFlush(log);
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    long end = System.currentTimeMillis();
                    log.setResponsetime((int)(end-start));
                    log.setCommite("执行失败");
                    logDao.saveAndFlush(log);
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}

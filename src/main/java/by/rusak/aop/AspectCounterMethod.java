package by.rusak.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
@Aspect
public class AspectCounterMethod {
  Map<String, Integer> mapCounterMethod = new TreeMap<>();

  @Pointcut("execution(* by.rusak.repository.jdbctemplate.JdbcTemplateCarRepository.*(..))")
  public void beforeRepositoryPointcut() {
  }

  @Before("beforeRepositoryPointcut()")
  public Map<String, Integer> logBefore(JoinPoint joinPoint) throws IOException {
    if (mapCounterMethod.containsKey(joinPoint.getSignature().getName())) {
      mapCounterMethod.put(joinPoint.getSignature().getName(), mapCounterMethod.get(joinPoint.getSignature().getName()) + 1);
    } else {
      mapCounterMethod.put(joinPoint.getSignature().getName(), 1);
    }
    //System.out.println(mapCounterMethod);
    writeFile();
    return mapCounterMethod;
  }

  public void writeFile () throws IOException {
    File file = new File ("C:/Users/A S U S/Desktop/LogCarRental/CountMethod.txt");
    FileWriter fw = new FileWriter(file);
    fw.write(mapCounterMethod.toString().toCharArray());
    fw.flush();
    fw.close();
  }

}


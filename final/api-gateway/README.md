#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
# ${artifactId}

> Шаблон проекта для создания микросервиса.

`Шаблон основан на `[**Генератор кода микросервисов**](https://conf.diasoft.ru/pages/viewpage.action?pageId=44771672)

  ```
  <plugin>
    <groupId>ru.diasoft.digitalq.codegen</groupId>
    <artifactId>dqcodegen-maven-plugin</artifactId>
    <version>${dqcodegen.version}</version> 
  <plugin>  
  ```

## Разработка

  - `Скопируйте проект`
  
    в **pom.xml** измените **groupId** и **artefactId** 
  
  - `Контракт сервиса`
  
    контракт сервиса будет создан из файла ***src\main\resources\swagger.yml*** 
    
    для этого в **pom.xml** описан ***execution Generate REST Controller by Swagger file***
    
    [**Генератор - описание контракта REST сервиса**](https://conf.diasoft.ru/pages/viewpage.action?pageId=50894444)
  
    ```
    <execution>
        <id>Generate REST Controller by Swagger file</id>
        <goals>
            <goal>local</goal>
        </goals>
        <configuration>
            <swaggerFile>${project.basedir}/src/main/resources/swagger.yml</swaggerFile> 
        </configuration>
    </execution>
    ```
  
  - `Контракт сервиса из Q.Archer`
  
    контракт сервиса можно получить из Q.Archer  
    
    для этого в **pom.xml** описан ***execution Generate REST Controller by Q.Archer***
    
    по умолчанию задача отключена
    
    для выполнения задачи нужно настроить 
      1. **qarcherModuleName** 
      2. **qarcherVersion** 
      3. убрать **skip = true**
    
    [**Генератор + Q.Archer**](conf.diasoft.ru/pages/viewpage.action?pageId=121474970)
  
    ```
    <execution>
        <id>Generate REST Controller by Q.Archer</id>
        <goals>
            <goal>qarcher</goal>
        </goals>
        <configuration>
            <skip>true</skip>                             
            <serviceName>QArcherDemo</serviceName> 
            <qarcherModuleName>DQ Mobile Investment Client Portfolio</qarcherModuleName>
            <qarcherVersion>1.01.01</qarcherVersion>
        </configuration>
    </execution>
    ```
  
  - `События. Event-Driven Architectures (EDA)`
  
    [**Генератор + Event-Driven Architectures (EDA)**](conf.diasoft.ru/pages/viewpage.action?pageId=50894482)
    
    Публикацию и подписку на события можно описать в одном файле и создать при выполниии одной задачи, а можно разделить, как сделано ниже.
    
    - `Публикация событий`
    
      контракт сервиса будет создан из файла ***src\main\resources\event-publisher.yml*** 
      
      для этого в **pom.xml** описан ***execution Generate Events Publisher***
      
      по умолчанию задача отключена
      
      для выполнения задачи нужно настроить 
        1. **asyncApiFile** 
        2. убрать **skip = true**
      
      ```
      <execution>
          <id>Generate Events Publisher</id>
          <goals>
              <goal>local</goal>
          </goals>
          <configuration>
              <skip>true</skip>                                                     
              <asyncApiFile>${project.basedir}/src/main/resources/event-publisher.yml</asyncApiFile>                            
          </configuration>
      </execution>
      ```
    
    - `Подписка на события`
    
      обработчики подписок на события будут созданы из файла ***src\main\resources\event-subscriber.yml*** 
      
      для этого в **pom.xml** описан ***execution Generate Events Subsriber***
      
      по умолчанию задача отключена
      
      для выполнения задачи нужно настроить 
        1. **asyncApiFile** 
        2. убрать **skip = true**
        
      ```
      <execution>
          <id>Generate Events Subsriber</id>
          <goals>
              <goal>local</goal>
          </goals>
          <configuration>
              <skip>true</skip>                                                     
              <asyncApiFile>${project.basedir}/src/main/resources/event-subscriber.yml</asyncApiFile>                            
          </configuration>
      </execution>
      ```
    
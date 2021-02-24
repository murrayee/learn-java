## IoC 容器

#### IoC原理

什么是 IoC?

IoC 全称 Inversion of Control，直译为控制反转。IoC 不是什么技术，而是一种设计思想,在 Java 开发中，Ioc 意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。IoC 又称为依赖注入（DI：Dependency Injection），它解决了一个最主要的问题：将组件的创建+配置与组件的使用相分离，并且，由 IoC 容器负责管理组件的生命周期。

谁控制谁，控制什么?

传统 Java SE 程序设计，我们直接在对象内部通过 new 进行创建对象，是程序主动去创建依赖对象；而 IoC 是有专门一个容器来创建这些对象，即由 Ioc 容器来控制对象的创建；谁控制谁？当然是 IoC 容器控制了对象；控制什么？那就是主要控制了外部资源获取（不只是对象包括比如文件等）。

为何是反转，哪些方面反转了?

有反转就有正转，传统应用程序是由我们自己在对象中主动控制去直接获取依赖对象，也就是正转；而反转则是由容器来帮忙创建及注入依赖对象；为何是反转？因为由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象，所以是反转；哪些方面反转了？依赖对象的获取被反转了。

#### 装配 Bean

- 使用 xml 方式 `application.xml`

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userService" class="com.itranswarp.learnjava.service.UserService">
        <property name="mailService" ref="mailService" />
    </bean>

    <bean id="mailService" class="com.itranswarp.learnjava.service.MailService" />
</beans>

```

- 创建 IoC 容器

```
    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
```

Spring 容器就是 ApplicationContext，它是一个接口，有很多实现类，这里我们选择 ClassPathXmlApplicationContext，表示它会自动从 classpath 中查找指定的 XML 配置文件。

#### 使用 annotation

我们给`MailService`添加一个`@Component`注解：

```
@Component
public class MailService {
    ...
}
```

这个`@Component` 注解就相当于定义了一个 Bean，它有一个可选的名称，默认是 mailService，即小写开头的类名。

我们给`UserService`添加一个@Component 注解和一个`@Autowired`注解：

```
@Component
public class UserService {
    @Autowired
    MailService mailService;

    ...
}

```

使用@Autowired 就相当于把指定类型的 Bean 注入到指定的字段中。和 XML 配置相比，`@Autowired` 大幅简化了注入，因为它不但可以写在 set()方法上，还可以直接写在字段上，甚至可以写在构造方法中：

```
@Component
public class UserService {
    MailService mailService;

    public UserService(@Autowired MailService mailService) {
        this.mailService = mailService;
    }
    ...
}

```

创建容器：

```
@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}
```

使用的实现类是 AnnotationConfigApplicationContext，必须传入一个标注了`@Configuration`的类名。

`AppConfig`还标注了`@ComponentScan`，它告诉容器，自动搜索当前类所在的包以及子包，把所有标注为`@Component`的 Bean 自动创建出来，并根据`@Autowired`进行装配。



#### 定制Bean

- @Scope

对于Spring容器来说，当我们把一个Bean标记为`@Component`后，它就会自动为我们创建一个单例（Singleton），即容器初始化时创建Bean，容器关闭前销毁Bean。在容器运行期间，我们调用`getBean(Class)`获取到的Bean总是同一个实例。

还有一种Bean，我们每次调用`getBean(Class)`，容器都返回一个新的实例，这种Bean称为Prototype（原型），它的生命周期显然和Singleton不同。声明一个Prototype的Bean时，需要添加一个额外的`@Scope`注解：

```
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // @Scope("prototype")
public class MailSession {
    ...
}
```

- 注入List

有些时候，我们会有一系列接口相同，不同实现类的Bean。例如，注册用户时，我们要对email、password和name这3个变量进行验证。为了便于扩展，我们先定义验证接口：

```
public interface Validator {
    void validate(String email, String password, String name);
}
```

然后，分别使用3个`Validator`对用户参数进行验证：

```
@Component
public class EmailValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!email.matches("^[a-z0-9]+\\@[a-z0-9]+\\.[a-z]{2,10}$")) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
    }
}

@Component
public class PasswordValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!password.matches("^.{6,20}$")) {
            throw new IllegalArgumentException("invalid password");
        }
    }
}

@Component
public class NameValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (name == null || name.isBlank() || name.length() > 20) {
            throw new IllegalArgumentException("invalid name: " + name);
        }
    }
}
```

最后，我们通过一个`Validators`作为入口进行验证：

```
@Component
public class Validators {
    @Autowired
    List<Validator> validators;

    public void validate(String email, String password, String name) {
        for (var validator : this.validators) {
            validator.validate(email, password, name);
        }
    }
}
```

注意到`Validators`被注入了一个`List<Validator>`，Spring会自动把所有类型为`Validator`的Bean装配为一个`List`注入进来，这样一来，我们每新增一个`Validator`类型，就自动被Spring装配到`Validators`中了，非常方便。

因为Spring是通过扫描classpath获取到所有的Bean，而`List`是有序的，要指定`List`中Bean的顺序，可以加上`@Order`注解：

```
@Component
@Order(1)
public class EmailValidator implements Validator {
    ...
}

@Component
@Order(2)
public class PasswordValidator implements Validator {
    ...
}

@Component
@Order(3)
public class NameValidator implements Validator {
    ...
}
```

- 可选注入

默认情况下，当我们标记了一个`@Autowired`后，Spring如果没有找到对应类型的Bean，它会抛出`NoSuchBeanDefinitionException`异常。

可以给`@Autowired`增加一个`required = false`的参数：

```
@Component
public class MailService {
    @Autowired(required = false)
    ZoneId zoneId = ZoneId.systemDefault();
    ...
}
```

这个参数告诉Spring容器，如果找到一个类型为`ZoneId`的Bean，就注入，如果找不到，就忽略。

这种方式非常适合有定义就使用定义，没有就使用默认值的情况。

创建第三方Bean

如果一个Bean不在我们自己的package管理之类，例如`ZoneId`，如何创建它？

答案是我们自己在`@Configuration`类中编写一个Java方法创建并返回它，注意给方法标记一个`@Bean`注解：

```
@Configuration
@ComponentScan
public class AppConfig {
    // 创建一个Bean:
    @Bean
    ZoneId createZoneId() {
        return ZoneId.of("Z");
    }
}
```

Spring对标记为`@Bean`的方法只调用一次，因此返回的Bean仍然是单例。

- 初始化和销毁

有些时候，一个Bean在注入必要的依赖后，需要进行初始化（监听消息等）。在容器关闭时，有时候还需要清理资源（关闭连接池等）。我们通常会定义一个`init()`方法进行初始化，定义一个`shutdown()`方法进行清理，然后，引入JSR-250定义的Annotation：

```
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.2</version>
</dependency>
```

在Bean的初始化和清理方法上标记`@PostConstruct`和`@PreDestroy`：

```
@Component
public class MailService {
    @Autowired(required = false)
    ZoneId zoneId = ZoneId.systemDefault();

    @PostConstruct
    public void init() {
        System.out.println("Init mail service with zoneId = " + this.zoneId);
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Shutdown mail service");
    }
}
```

Spring容器会对上述Bean做如下初始化流程：

- 调用构造方法创建`MailService`实例；
- 根据`@Autowired`进行注入；
- 调用标记有`@PostConstruct`的`init()`方法进行初始化。

而销毁时，容器会首先调用标记有`@PreDestroy`的`shutdown()`方法。

Spring只根据Annotation查找*无参数*方法，对方法名不作要求。

- 使用别名

默认情况下，对一种类型的Bean，容器只创建一个实例。但有些时候，我们需要对一种类型的Bean创建多个实例。例如，同时连接多个数据库，就必须创建多个`DataSource`实例。

如果我们在`@Configuration`类中创建了多个同类型的Bean：

```
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
```

Spring会报`NoUniqueBeanDefinitionException`异常，意思是出现了重复的Bean定义。

这个时候，需要给每个Bean添加不同的名字：

```
@Configuration
@ComponentScan
public class AppConfig {
    @Bean("z")
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
```

可以用`@Bean("name")`指定别名，也可以用`@Bean`+`@Qualifier("name")`指定别名。

存在多个同类型的Bean时，注入`ZoneId`又会报错：

```
NoUniqueBeanDefinitionException: No qualifying bean of type 'java.time.ZoneId' available: expected single matching bean but found 2
```

意思是期待找到唯一的`ZoneId`类型Bean，但是找到两。因此，注入时，要指定Bean的名称：

```
@Component
public class MailService {
	@Autowired(required = false)
	@Qualifier("z") // 指定注入名称为"z"的ZoneId
	ZoneId zoneId = ZoneId.systemDefault();
    ...
}
```

还有一种方法是把其中某个Bean指定为`@Primary`：

```
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Primary // 指定为主要Bean
    @Qualifier("z")
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
```

这样，在注入时，如果没有指出Bean的名字，Spring会注入标记有`@Primary`的Bean。这种方式也很常用。例如，对于主从两个数据源，通常将主数据源定义为`@Primary`：

```
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Primary
    DataSource createMasterDataSource() {
        ...
    }

    @Bean
    @Qualifier("slave")
    DataSource createSlaveDataSource() {
        ...
    }
}
```

其他Bean默认注入的就是主数据源。如果要注入从数据源，那么只需要指定名称即可。

- 使用FactoryBean

我们在设计模式的[工厂方法](https://www.liaoxuefeng.com/wiki/1252599548343744/1281319170474017)中讲到，很多时候，可以通过工厂模式创建对象。Spring也提供了工厂模式，允许定义一个工厂，然后由工厂创建真正的Bean。

用工厂模式创建Bean需要实现`FactoryBean`接口。我们观察下面的代码：

```
@Component
public class ZoneIdFactoryBean implements FactoryBean<ZoneId> {

    String zone = "Z";

    @Override
    public ZoneId getObject() throws Exception {
        return ZoneId.of(zone);
    }

    @Override
    public Class<?> getObjectType() {
        return ZoneId.class;
    }
}
```

当一个Bean实现了`FactoryBean`接口后，Spring会先实例化这个工厂，然后调用`getObject()`创建真正的Bean。`getObjectType()`可以指定创建的Bean的类型，因为指定类型不一定与实际类型一致，可以是接口或抽象类。

因此，如果定义了一个`FactoryBean`，要注意Spring创建的Bean实际上是这个`FactoryBean`的`getObject()`方法返回的Bean。为了和普通Bean区分，我们通常都以`XxxFactoryBean`命名。



#### 使用Resource

Spring提供了一个`org.springframework.core.io.Resource`（注意不是`javax.annotation.Resource`），它可以像`String`、`int`一样使用`@Value`注入：

```
@Component
public class AppService {
    @Value("classpath:/logo.txt")
    private Resource resource;

    private String logo;

    @PostConstruct
    public void init() throws IOException {
        try (var reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            this.logo = reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
```

注入`Resource`最常用的方式是通过classpath，即类似`classpath:/logo.txt`表示在classpath中搜索`logo.txt`文件，然后，我们直接调用`Resource.getInputStream()`就可以获取到输入流，避免了自己搜索文件的代码。

也可以直接指定文件的路径，例如：

```
@Value("file:/path/to/logo.txt")
private Resource resource;
```



#### 注入配置

Spring容器还提供了一个更简单的`@PropertySource`来自动读取配置文件。我们只需要在`@Configuration`配置类上再添加一个注解：

```
@Configuration
@ComponentScan
@PropertySource("app.properties") // 表示读取classpath的app.properties
public class AppConfig {
    @Value("${app.zone:Z}")
    String zoneId;

    @Bean
    ZoneId createZoneId() {
        return ZoneId.of(zoneId);
    }
}
```

Spring容器看到`@PropertySource("app.properties")`注解后，自动读取这个配置文件，然后，我们使用`@Value`正常注入：

```
@Value("${app.zone:Z}")
String zoneId;
```

注意注入的字符串语法，它的格式如下：

- `"${app.zone}"`表示读取key为`app.zone`的value，如果key不存在，启动将报错；
- `"${app.zone:Z}"`表示读取key为`app.zone`的value，但如果key不存在，就使用默认值`Z`。

这样一来，我们就可以根据`app.zone`的配置来创建`ZoneId`。

还可以把注入的注解写到方法参数中：

```
@Bean
ZoneId createZoneId(@Value("${app.zone:Z}") String zoneId) {
    return ZoneId.of(zoneId);
}
```

可见，先使用`@PropertySource`读取配置文件，然后通过`@Value`以`${key:defaultValue}`的形式注入，可以极大地简化读取配置的麻烦。

另一种注入配置的方式是先通过一个简单的JavaBean持有所有的配置，例如，一个`SmtpConfig`：

```
@Component
public class SmtpConfig {
    @Value("${smtp.host}")
    private String host;

    @Value("${smtp.port:25}")
    private int port;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
```

然后，在需要读取的地方，使用`#{smtpConfig.host}`注入：

```
@Component
public class MailService {
    @Value("#{smtpConfig.host}")
    private String smtpHost;

    @Value("#{smtpConfig.port}")
    private int smtpPort;
}
```

注意观察`#{}`这种注入语法，它和`${key}`不同的是，`#{}`表示从JavaBean读取属性。`"#{smtpConfig.host}"`的意思是，从名称为`smtpConfig`的Bean读取`host`属性，即调用`getHost()`方法。一个Class名为`SmtpConfig`的Bean，它在Spring容器中的默认名称就是`smtpConfig`，除非用`@Qualifier`指定了名称。

使用一个独立的JavaBean持有所有属性，然后在其他Bean中以`#{bean.property}`注入的好处是，多个Bean都可以引用同一个Bean的某个属性。例如，如果`SmtpConfig`决定从数据库中读取相关配置项，那么`MailService`注入的`@Value("#{smtpConfig.host}")`仍然可以不修改正常运行。



#### 使用条件装配

Spring为应用程序准备了Profile这一概念，用来表示不同的环境。例如，我们分别定义开发、测试和生产这3个环境：

- native
- test
- production

创建某个Bean时，Spring容器可以根据注解`@Profile`来决定是否创建。例如，以下配置：

```
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Profile("!test")
    ZoneId createZoneId() {
        return ZoneId.systemDefault();
    }

    @Bean
    @Profile("test")
    ZoneId createZoneIdForTest() {
        return ZoneId.of("America/New_York");
    }
}
```

如果当前的Profile设置为`test`，则Spring容器会调用`createZoneIdForTest()`创建`ZoneId`，否则，调用`createZoneId()`创建`ZoneId`。注意到`@Profile("!test")`表示非test环境。

在运行程序时，加上JVM参数`-Dspring.profiles.active=test`就可以指定以`test`环境启动。

实际上，Spring允许指定多个Profile，例如：

```
-Dspring.profiles.active=test,master
```

可以表示`test`环境，并使用`master`分支代码。

要满足多个Profile条件，可以这样写：

```
@Bean
@Profile({ "test", "master" }) // 同时满足test和master
ZoneId createZoneId() {
    ...
}
```

使用Conditional

除了根据`@Profile`条件来决定是否创建某个Bean外，Spring还可以根据`@Conditional`决定是否创建某个Bean。

例如，我们对`SmtpMailService`添加如下注解：

```
@Component
@Conditional(OnSmtpEnvCondition.class)
public class SmtpMailService implements MailService {
    ...
}
```

它的意思是，如果满足`OnSmtpEnvCondition`的条件，才会创建`SmtpMailService`这个Bean。`OnSmtpEnvCondition`的条件是什么呢？我们看一下代码：

```
public class OnSmtpEnvCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "true".equalsIgnoreCase(System.getenv("smtp"));
    }
}
```

因此，`OnSmtpEnvCondition`的条件是存在环境变量`smtp`，值为`true`。这样，我们就可以通过环境变量来控制是否创建`SmtpMailService`。

Spring只提供了`@Conditional`注解，具体判断逻辑还需要我们自己实现。Spring Boot提供了更多使用起来更简单的条件注解，例如，如果配置文件中存在`app.smtp=true`，则创建`MailService`：

```
@Component
@ConditionalOnProperty(name="app.smtp", havingValue="true")
public class MailService {
    ...
}
```

如果当前classpath中存在类`javax.mail.Transport`，则创建`MailService`：

```
@Component
@ConditionalOnClass(name = "javax.mail.Transport")
public class MailService {
    ...
}
```

后续我们会介绍Spring Boot的条件装配。我们以文件存储为例，假设我们需要保存用户上传的头像，并返回存储路径，在本地开发运行时，我们总是存储到文件：

```
@Component
@ConditionalOnProperty(name = "app.storage", havingValue = "file", matchIfMissing = true)
public class FileUploader implements Uploader {
    ...
}
```

在生产环境运行时，我们会把文件存储到类似AWS S3上：

```
@Component
@ConditionalOnProperty(name = "app.storage", havingValue = "s3")
public class S3Uploader implements Uploader {
    ...
}
```

其他需要存储的服务则注入`Uploader`：

```
@Component
public class UserImageService {
    @Autowired
    Uploader uploader;
}
```

当应用程序检测到配置文件存在`app.storage=s3`时，自动使用`S3Uploader`，如果存在配置`app.storage=file`，或者配置`app.storage`不存在，则使用`FileUploader`。

可见，使用条件注解，能更灵活地装配Bean。
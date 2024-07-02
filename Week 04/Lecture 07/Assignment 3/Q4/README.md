## 💡 Differences between BeanFactory and ApplicationContext

### 👩‍💻 The Definition

Spring Framework provides two of the most fundamental and important packages, that are the `org.springframework.beans` and `org.springframework.context` packages.

Code in these packages provides the basis for Spring’s **Inversion of Control (IOC)/Dependency Injection (DI)** features. Spring containers are responsible for creating bean objects and injecting them into the classes. The two containers are:

1. **`BeanFactory`:** Available in `org.springframework.beans.factory` package. This is the root interface for accessing a Spring bean container. It is the actual container that instantiates, configures, and manages a number of beans
2. **`ApplicationContext`:** Available in `org.springframework.context` package. The `ApplicationContext` interface is the advanced container that enhances `BeanFactory` functionality in a more framework-oriented style. 

---

### 👩‍🏫 The Differences

| BeanFactory | ApplicationContext |
| --- | --- |
| It is a fundamental container that provides the basic functionality for managing beans. | It is an advanced container that extends the BeanFactory that provides all basic functionality and adds some advanced features. |
| It is suitable to build standalone applications. | It is suitable to build Web applications, integration with AOP modules, ORM and distributed applications. |
| It supports only Singleton and Prototype bean scopes. | It supports all types of bean scopes such as Singleton, Prototype, Request, Session etc. |
| It does not support Annotations. In Bean Autowiring, we need to configure the properties in XML file only. | It supports Annotation based configuration in Bean Autowiring. |
| This interface does not provides messaging (i18n or internationalization) functionality. | ApplicationContext interface extends MessageSource interface, thus it provides messaging (i18n or internationalization) functionality. |
| BeanFactory does not support Event publication functionality. | Event handling in the ApplicationContext is provided through the ApplicationEvent class and ApplicationListener interface. |
| In BeanFactory, we need to manually register BeanPostProcessors and BeanFactoryPostProcessors. | The ApplicationContext automatically registers BeanFactoryPostProcessor and BeanPostProcessor at startup. |
| BeanFactory will create a bean object when the getBean() method is called thus making it Lazy initialization. | ApplicationContext loads all the beans and creates objects at the time of startup only thus making it Eager initialization. |
| BeanFactory interface provides basic features only thus requires less memory. For standalone applications where the basic features are enough and when memory consumption is critical, we can use BeanFactory. | ApplicationContext provides all the basic features and advanced features, including several that are geared towards enterprise applications thus requires more memory. |

---

### 💻 Summary

- Both `ApplicationContext` and `BeanFactory` containers are used to create and manage the bean objects
- `BeanFactory` is the fundamental interface that provides all the basic functionality to create and manage the bean objects
- `ApplicationContext` interface extends the `BeanFactory` interface. It provides all the basic functionality and also some advanced features like the ability to load file resources in a generic fashion, to publish events to registered listeners, to resolve messages, supporting internationalization, etc
- Therefore, it’s always preferable to use ****`ApplicationContext` **when building complex enterprise applications.
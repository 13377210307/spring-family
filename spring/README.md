一：Spring常用注解

1：@Controller：用于标注控制层组件

2：@RestController：相当于@Controller和@ResponseBody的组合效果

3：@Component：泛指组件

4：@Service：用于标注业务层组件

5：@RequestMapping：用于处理请求地址映射的注解

6：@Autowired：用于完成自动装配的工作。

二：Spring Bean作用域

1：singleton：单例模式，Spring IOC容器中只会存在一个共享的Bean实例

2：prototype：原型模式，每次使用时就会创建一个新的bean实例

3：Request：在一次http请求中，容器会返回该bean的同一实例，对于不同的http请求则会产生新的bean；http请求结束后会销毁bean实例

4：session：在一次http session中，容器会返回该bean的同一实例，对于不同的session则会产生新的bean；http请求结束后会销毁bean实例

5：global Session：在全局的http Session中，容器会返回该bean的同一个实例。

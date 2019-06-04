ssos框架，登入、验证、加密、权限的集合体
新增加flowable工作流，以及破解modeler权限验证功能,模块分层
架构基础-springboot、shiro、mybatis、flowable
原则:为了最简单所以更简单

~~~~
ssos-framework

--------flowable-spring-boot-starter 工作流模块 

--------ssos-mybatisUtils mybatis模块  简单的简化了mybatis insert update selelct操作

--------ssos-web web核心业务模块 包含了shrio认证授权操作，使用token做到了无状态http请求、后期会专门把这个认证模块提取出来

--------ssos-exeption 自定义异常模块 目前就只有一个自定义异常

--------ssos-formengine 表单引擎模块 为了迎合多表单填写浏览，做到动态建表、动态展现

--------ssos-spring-boot-starter 框架核心模块，加入项目即可使用，所有模块的集合

--------ssos-formengineV2 自定义表单、动态字段第二种解决方案
~~~~

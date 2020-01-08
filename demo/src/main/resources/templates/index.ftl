<html>
<!--一定要导入spring.ftl-->
<#--<#import "spring.ftl" as spring>-->

<body>
<!--或者<@spring.message code="username" />-->
<@spring.message "username" />

<!--arg是一个在freemarker中定义的数组,包含了占位符{0},{1}对应的参数 -->
<#assign arg = ["我的首页","张三"]>
<@spring.messageArgs "title" arg />

</body>
</html>
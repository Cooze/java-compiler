<#if packageName?? >
package ${packageName};
</#if>

<#if importPackages?? >
    <#list importPackages as importPackage>
import  ${importPackage.packageName!};
    </#list>
</#if>

<#if annotations?? >
    <#list annotations as anno >
${anno.annotation}
    </#list>
</#if>
<#if modifier?? > ${modifier} </#if><#if className?? > ${className} </#if><#if superClass?? > extends ${superClass} </#if><#if interfaces??>implements<#list interfaces as inter > <#if (inter_index!=(interfaces?size -1 )) > ${inter.interfaces},<#else>${inter.interfaces}</#if></#list></#if> {

<#if attributes?? >
    <#list attributes as attr>
    <#if attr.annotations?? >
        <#list attr.annotations as attrAnno >
    ${attrAnno.annotation}
        </#list>
    </#if>
    <#if attr.modifier?? >${attr.modifier}</#if> <#if attr.type?? >${attr.type}</#if> <#if attr.name?? >${attr.name}</#if>;

    <#if (attr.gs?? && attr.gs=="all" )>
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
        this.${attr.name} = ${attr.name};
    }
    public ${attr.type} get${attr.name?cap_first}(){
        return this.${attr.name};
    }
    <#elseif (attr.gs?? && attr.gs=="getter" )>
    public ${attr.type} get${attr.name?cap_first}(){
        return this.${attr.name};
    }
    <#elseif (attr.gs?? && attr.gs=="setter" )>
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
        this.${attr.name} = ${attr.name};
    }
    </#if>
    </#list>
</#if>

<#if methods??>
    <#list methods as method>
    <#if method.annotations??>
        <#list method.annotations as methodAnno>
    ${methodAnno.annotation}
        </#list>
    </#if>
    <#if method.modifier??>${method.modifier} </#if> <#if method.returnType??>${method.returnType} </#if> <#if method.name??>${method.name}</#if>(<#if method.methodParameters??><#list method.methodParameters as parameter><#if ((method.methodParameters?size-1)!= parameter_index) ><#if parameter.modifier??>${parameter.modifier}</#if> <#if parameter.type??>${parameter.type}</#if> <#if parameter.name??>${parameter.name}</#if>,<#else> <#if parameter.modifier??>${parameter.modifier} </#if><#if parameter.type??>${parameter.type}</#if> <#if parameter.name??>${parameter.name}</#if></#if></#list> </#if>) <#if method.throwsExceptions??> ${method.throwsExceptions} </#if>{
        <#if method.methodBody??>
            ${ method.methodBody }
        </#if>
    }
    </#list>

</#if>

}
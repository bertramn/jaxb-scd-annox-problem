JAXB SCD Not Working with ANNOX Example
======================

This very simple example demonstrates that JAXB XJC is not working with with jaxb2 commons annotate plugin when the binding is using SCD.

## Test 1

`mvn clean install`

Using the standard binding file with normal schema type binding works ok. The class name for xml:complexType SongType is overridden to Song and the class has a @Deprecated annotation.

```
#!xml
<jaxb:bindings node="/xsd:schema" schemaLocation="example.xsd">
  <jaxb:bindings node="xsd:complexType[@name='SongType']">
    <jaxb:class name="Song"/>
    <annox:annotateClass>
      <annox:annotate annox:class="java.lang.Deprecated"/>
    </annox:annotateClass>
  </jaxb:bindings>
</jaxb:bindings>
```

## Test 2

`mvn clean install -Pscd`

Using SCD to locate a XML type and renaming it works a treat too using the 2nd profile. The example again shows that class name for xml:complexType SongType is overridden to Song. The @Deprecated annotation was not added during this test run.

```
#!xml
<jaxb:bindings scd="x-schema::tns" xmlns:tns="http://www.fleurida.com/blueprint">
  <jaxb:bindings scd="~tns:SongType">
    <jaxb:class name="Song"/>
  </jaxb:bindings>
</jaxb:bindings>
```

## Test 3

`mvn clean install -Pscd-annox`

Trying the same thing as done in test 1 on the SCD example which works with scd, we will get below error stack.

```
#!xml
<jaxb:bindings scd="x-schema::tns" xmlns:tns="http://www.fleurida.com/blueprint">
  <jaxb:bindings scd="~tns:SongType">
    <jaxb:class name="Song"/>
    <annox:annotateClass>
      <annox:annotate annox:class="java.lang.Deprecated"/>
    </annox:annotateClass>
  </jaxb:bindings>
</jaxb:bindings>
```

Error Stack
```
[INFO] Parsing input schema(s)...
[ERROR] Error while parsing schema(s).Location [ file:/dev/oss/jaxb-scd-annox-problem/src/main/resources/scd.annox.jaxb.xml{8,25}].
org.xml.sax.SAXParseException; systemId: file:/dev/oss/jaxb-scd-annox-problem/src/main/resources/scd.annox.jaxb.xml; lineNumber: 8; columnNumber: 25; cvc-elt.1: Cannot find the declaration of element 'annox:annotateClass'.
        at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:198)
        at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.error(ErrorHandlerWrapper.java:134)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:437)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:368)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:325)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.handleStartElement(XMLSchemaValidator.java:1906)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.startElement(XMLSchemaValidator.java:746)
        at com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHandlerImpl.startElement(ValidatorHandlerImpl.java:570)
        at com.sun.tools.xjc.util.ForkContentHandler.startElement(ForkContentHandler.java:114)
        at org.xml.sax.helpers.XMLFilterImpl.startElement(XMLFilterImpl.java:551)
        at com.sun.tools.xjc.reader.internalizer.DOMForestScanner$LocationResolver.startElement(DOMForestScanner.java:147)
        at com.sun.xml.bind.unmarshaller.DOMScanner.visit(DOMScanner.java:244)
        at com.sun.xml.bind.unmarshaller.DOMScanner.scan(DOMScanner.java:127)
        at com.sun.tools.xjc.reader.internalizer.DOMForestScanner.scan(DOMForestScanner.java:92)
        at com.sun.tools.xjc.reader.internalizer.SCDBasedBindingSet$Target.apply(SCDBasedBindingSet.java:184)
        at com.sun.tools.xjc.reader.internalizer.SCDBasedBindingSet$Target.applyAll(SCDBasedBindingSet.java:142)
        at com.sun.tools.xjc.reader.internalizer.SCDBasedBindingSet$Target.apply(SCDBasedBindingSet.java:163)
        at com.sun.tools.xjc.reader.internalizer.SCDBasedBindingSet$Target.applyAll(SCDBasedBindingSet.java:142)
        at com.sun.tools.xjc.reader.internalizer.SCDBasedBindingSet$Target.access$700(SCDBasedBindingSet.java:86)
        at com.sun.tools.xjc.reader.internalizer.SCDBasedBindingSet.apply(SCDBasedBindingSet.java:241)
        at com.sun.tools.xjc.ModelLoader.createXSOM(ModelLoader.java:541)
        at com.sun.tools.xjc.ModelLoader.loadXMLSchema(ModelLoader.java:378)
        at com.sun.tools.xjc.ModelLoader.load(ModelLoader.java:174)
        at com.sun.tools.xjc.ModelLoader.load(ModelLoader.java:119)
        at org.jvnet.mjiip.v_2_2.XJC22Mojo.loadModel(XJC22Mojo.java:58)
        at org.jvnet.mjiip.v_2_2.XJC22Mojo.doExecute(XJC22Mojo.java:44)
        at org.jvnet.mjiip.v_2_2.XJC22Mojo.doExecute(XJC22Mojo.java:29)
        at org.jvnet.jaxb2.maven2.RawXJC2Mojo.doExecute(RawXJC2Mojo.java:364)
        at org.jvnet.jaxb2.maven2.RawXJC2Mojo.execute(RawXJC2Mojo.java:161)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:133)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:208)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:153)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:145)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:108)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:76)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build(SingleThreadedBuilder.java:51)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:116)
        at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:361)
        at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:155)
        at org.apache.maven.cli.MavenCli.execute(MavenCli.java:584)
        at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:213)
        at org.apache.maven.cli.MavenCli.main(MavenCli.java:157)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:606)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:289)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:229)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:415)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:356)
```

ThisBuild / organization := "io.simplifier"
ThisBuild / version := sys.env.get("VERSION").getOrElse("NA")
ThisBuild / scalaVersion := "2.12.15"

ThisBuild / useCoursier := true


lazy val root = (project in file("."))
  .settings(
    name := "CaptchaPlugin",
    assembly / assemblyJarName := "captcha.jar",
    assembly / test := {},
    assembly / assemblyMergeStrategy := {
      case x if x.endsWith("module-info.class") => MergeStrategy.discard
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    },
    libraryDependencies ++= Seq(
      "io.github.simplifier-ag" %% "simplifier-plugin-base" % "1.0.2" withSources(),
      "com.github.penggle" % "kaptcha" % "2.3.2" withSources() withJavadoc(),
      "org.scalatest" %% "scalatest" % "3.1.4" % "test" withSources() withJavadoc(),
    )
  )


//Security Options for Java >= 18
lazy val requireAddOpensPackages = Seq(
  "java.base/java.lang",
  "java.base/java.util",
  "java.base/java.time",
  "java.base/java.lang.invoke",
  "java.base/sun.security.jca"
)
lazy val requireAddExportsPackages = Seq(
  "java.xml/com.sun.org.apache.xalan.internal.xsltc.trax"
)

assembly / packageOptions +=
  Package.ManifestAttributes(
    "Add-Opens" -> requireAddOpensPackages.mkString(" "),
    "Add-Exports" -> requireAddExportsPackages.mkString(" "),
    "Class-Path" -> (file("lib") * "*.jar").get.mkString(" ")
  )

run / javaOptions ++=
  requireAddOpensPackages.map("--add-opens=" + _ + "=ALL-UNNAMED") ++
    requireAddExportsPackages.map("--add-exports=" + _ + "=ALL-UNNAMED")

run / fork := true



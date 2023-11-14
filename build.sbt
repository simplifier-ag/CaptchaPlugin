
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
      "io.github.simplifier-ag" %% "simplifier-plugin-base" % "1.0.0" withSources(),
      "com.github.penggle" % "kaptcha" % "2.3.2" withSources() withJavadoc(),
      "org.scalatest" %% "scalatest" % "3.1.4" % "test" withSources() withJavadoc(),
    )
  )



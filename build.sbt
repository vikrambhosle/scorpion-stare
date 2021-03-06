
val PROJECT_NAME = "scorpion-stare"

val SPARK_VERSION = "2.0.1-scorpion-stare-SNAPSHOT"

def commonSettings = Seq(
  organization := "io.radanalytics",
  licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0")),
  version := "0.0.1-SNAPSHOT",
  libraryDependencies ++= Seq(
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
    "org.apache.spark" %% "spark-core" % SPARK_VERSION % "provided"
    ),
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt"),
  seq(bintraySettings:_*),
  seq(bintrayPublishSettings:_*),
  test in assembly := {},
  assemblyShadeRules in assembly := Seq(
    ShadeRule.zap("scala.**").inAll,
    ShadeRule.zap("org.slf4j.**").inAll
    )
)

lazy val core = project.in(file("core"))
  .settings(commonSettings:_*)
  .settings(
    name := s"$PROJECT_NAME-core"
    )

lazy val oshinko = project.in(file("oshinko"))
  .dependsOn(core)
  .settings(commonSettings:_*)
  .settings(
    name := s"$PROJECT_NAME-oshinko",
    assemblyJarName in assembly := "scorpion_stare.jar"
    )

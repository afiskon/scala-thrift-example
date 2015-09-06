name := "thrift-example"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-Xmax-classfile-name", "100")

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.9.2",
  "com.twitter" %% "scrooge-core" % "3.20.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
  )

com.twitter.scrooge.ScroogeSBT.newSettings
package com.ru.waka

import net.ruippeixotog.scalascraper.browser._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

object Main {
  val dummy = """
  <!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="UTF-8">
        <title>dummy</title>
      </head>
      <body>
        <h1>h1tag</h1>
        <div class="contents">
          <ul>
            <li>a</li>
            <li>b</li>
            <li>c</li>
          </ul>
        </div>
        <div id="aaa">
        </div>
      </body>
      <script>
        var span  = document.createElement("span");
        span.innerText= "aaa";
        document.getElementById("aaa").appendChild(span)
      </script>
    </html>
  """
  val jsoupBrowser = JsoupBrowser()
  val htmlUnitBrowser = HtmlUnitBrowser()
  def main(args: Array[String]): Unit = {
    val doc = jsoupBrowser.parseString(dummy)
    println("=== JsoupBrowser ===")
    println(doc >> text("title"))
    println(doc >> text("h1"))
    println(doc >> elementList("li"))
    println((doc >> elementList("li")).map(_.text))
    println(doc >> attr("charset")("meta"))
    println(doc >?> attr("charset")("title"))
    println(doc >?> text("#aaa span"))


    println("=== HtmlUnitBrowser ===")
    val doc2 = htmlUnitBrowser.parseString(dummy)
    println(doc2 >> text("title"))
    println(doc2 >> text("h1"))
    println(doc2 >> elementList("li"))
    println(doc2 >> attr("charset")("meta"))
    println(doc2 >?> attr("charset")("title"))
    println(doc2 >?> text("#aaa span"))

  }
}

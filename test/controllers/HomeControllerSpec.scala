/*
 * Copyright 2021 Linked Ideal LLC.[https://linked-ideal.com/]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers

import com.ideal.linked.toposoid.knowledgebase.nlp.model.SynonymList
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._
import play.api.Play.materializer

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController POST(word is empty)" should {
    "returns an appropriate response" in {
      val controller: HomeController = inject[HomeController]
      val jsonStr:String = """{ "word":"" }""".stripMargin
      val fr = FakeRequest(POST, "/getSynonyms")
        .withHeaders("Content-type" -> "application/json")
        .withJsonBody(Json.parse(jsonStr))
      val result= call(controller.getSynonyms(), fr)
      status(result) mustBe OK
      val jsonResult:String = contentAsJson(result).toString()
      val synonyms:SynonymList = Json.parse(jsonResult).as[SynonymList]
      assert(synonyms.synonyms.size == 0)
    }
  }

  "HomeController POST(a word)" should {
    "returns an appropriate response" in {
      val controller: HomeController = inject[HomeController]
      val jsonStr:String = """{ "word":"映画" }""".stripMargin
      val fr = FakeRequest(POST, "/getSynonyms")
        .withHeaders("Content-type" -> "application/json")
        .withJsonBody(Json.parse(jsonStr))
      val result= call(controller.getSynonyms(), fr)
      status(result) mustBe OK
      val jsonResult:String = contentAsJson(result).toString()
      val synonyms:SynonymList = Json.parse(jsonResult).as[SynonymList]
      assert(synonyms.synonyms.head == "フィルム")
    }
  }

}

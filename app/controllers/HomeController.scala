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

import com.ideal.linked.common.nlp.japanese.word2vec.Word2VecAccessor
import com.ideal.linked.common.nlp.japanese.wordnet.WordNetAccessor
import com.ideal.linked.toposoid.knowledgebase.nlp.model.{NormalizedWord, SynonymList}
import com.typesafe.scalalogging.LazyLogging

import javax.inject._
import play.api._
import play.api.libs.json.Json
import play.api.mvc._

import scala.collection.immutable.Set

/**
 * This controller creates "actions" to provide the functionality of the NLP common module.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController  with LazyLogging{

  /**
   * If you request a Japanese word with json as input, a list of its synonyms will be output as Json.
   */
  def getSynonyms= Action(parse.json) { request =>
    try {
      val json = request.body
      val normalizedName:NormalizedWord  = Json.parse(json.toString()).as[NormalizedWord]
      val synonyms: Set[String] = WordNetAccessor.getSynonyms(normalizedName.word)
      val selectedSynonyms = synonyms.filter(Word2VecAccessor.calcSimilarityByWord2Vec(normalizedName.word , _)).toList
      Ok(Json.toJson(SynonymList(selectedSynonyms))).as(JSON)
    }catch{
      case e: Exception => {
        logger.error(e.toString, e)
        BadRequest(Json.obj("status" ->"Error", "message" -> e.toString()))
      }
    }
  }
}

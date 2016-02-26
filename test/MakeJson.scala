import java.io.File

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json
import play.api.test.WithApplication

import scala.sys.process._

case class Fuga(a: String, b: List[Int])
object Fuga { implicit def jsonWrites = Json.writes[Fuga] } // おまじない

case class Hoge(foo: String, bar: Int, fuga: Fuga)
object Hoge { implicit def jsonWrites = Json.writes[Hoge] } // おまじない

@RunWith(classOf[JUnitRunner])
class MakeJson extends Specification {

  "Application" should {

    "make json" in new WithApplication {

      // JSONデータを作成
      val obj = Hoge("あああ", 100, Fuga("ううう", List(0, 1, 2, 3)))
      val json = Json.toJson(obj)

      // ファイル出力
      val f = new File("result.json")
      s"echo $json" #> f!
    }

  }
}

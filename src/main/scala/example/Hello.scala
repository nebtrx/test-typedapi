package example
import cats.effect.IO
import io.circe.generic.auto._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.{EntityDecoder, EntityEncoder}
import typedapi.server._
import typedapi.server.http4s._


object Hello extends App {

  final case class User(name: String)

  implicit val decoder: EntityDecoder[IO, User] = jsonOf[IO, User]
  implicit val encoder: EntityEncoder[IO, User] = jsonEncoderOf[IO, User]
  implicit val encoder1: EntityEncoder[IO, Option[User]] = jsonEncoderOf[IO, Option[User]]

  val Api =
    (:= :> "user" :> Segment[String]('name) :> Get[Option[User]]) :|:
    (:= :> "user" :> ReqBody[User] :> Post[User])

  var users: Map[String, User] = Map.empty

  val find : (String) => IO[Option[User]] = (name:String) =>  IO.pure(users.get(name))
  val create: User =>  IO[User] = (user: User) => {

    // Nasty use of mutable vars for the sake of simplicity
    users = users + (user.name -> user)
    IO.pure(user)
  }

  val endpoints = link(Api).to[IO](find :|: create :|: =:)

  val sm     = ServerManager(BlazeBuilder[IO], "localhost", 3000)
  val server = mount(sm, endpoints)

  server.unsafeRunSync()

  // Lazy wait for a keystroke for the sake of simplicity
  readLine()
}


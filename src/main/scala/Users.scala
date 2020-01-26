trait Users {
  case class User(id: Int, firstname: String, lastname: String, email: String)
  case class UserId(id: Int)
}
//val me = User2(1, "jl", "deja", "jldeja@gmail.com")
//val jl = User2(2, "antoine", "fszta", "fsta@gmail.com")

////  listTables(connection)
//  selectAllFromTable(connection, "users_info")
//  createTable(connection)
//insertIntoTable(connection,jl)
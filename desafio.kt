import java.time.LocalDate

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, val email: String, val dataNascimento: LocalDate)
data class ConteudoEducacional(var nome: String, val duracao: Int = 60) {
    override fun toString() : String {
        return "\nConteúdo: $nome\nduração: $duracao"
    }
}

data class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
       	val dataNascimento: LocalDate = usuario.dataNascimento
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw Exception("Data de nascimento nao pode ser superior ao dia atual!")
        }
        
        inscritos.add(usuario)
        println("Usuario adicionado a formacao $nome")
    }
   
   	override fun toString() : String {
        return "Formacao: $nome\nNivel: $nivel\nConteúdos: $conteudos\n"
    }
}

fun main() {
    val usuarioUm = Usuario("Fulano de tal", "fulanoDeTal@bol.com.br", LocalDate.parse("1999-11-01"))
    val usuarioDois = Usuario("Fulano de tal", "fulanoDeTal@bol.com.br", LocalDate.parse("1999-11-01"))
  	val usuarioTres = Usuario("Fulano de tal", "fulanoDeTal@bol.com.br", LocalDate.parse("2024-11-01"))  
    
    val conteudoEducacionalUm = ConteudoEducacional("Kotlin")
    val conteudoEducacionalDois = ConteudoEducacional("Java")
    val conteudoEducacionalTres = ConteudoEducacional("Go", 80)
    
    val formacao = Formacao("backend", Nivel.INTERMEDIARIO, listOf(conteudoEducacionalUm, conteudoEducacionalDois, conteudoEducacionalTres))
    
    try {
        formacao.matricular(usuarioUm)
    	formacao.matricular(usuarioDois)
        formacao.matricular(usuarioTres)
    } catch (e: Exception) {
        println("Ocorreu um problema ao adiconar um novo usuario. erro: ${e.message}")
    } finally {
        println(formacao)
    }
}

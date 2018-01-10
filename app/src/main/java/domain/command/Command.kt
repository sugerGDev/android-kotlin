package domain.command

interface Command<out T> {
    fun execute() : T
}
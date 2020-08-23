package javafx.example

import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.epistatic.app1.Main

// Note the the Gradle jfxRun task directly launches the JavaFxExample
// class, and bypasses this main function. This function exists so
// you can easily launch the application with a debugger attached.
fun main(args: Array<String>)
{
    launch(JavaFXExample::class.java)
}

class JavaFXExample : Application()
{
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("/app1/app1.fxml"))
        primaryStage.title = "Keyword tool for TravelSource LLC [(c)2020]"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java, *args)
        }
    }
}
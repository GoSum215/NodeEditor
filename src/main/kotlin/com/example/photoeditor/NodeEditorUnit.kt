package com.example.photoeditor

import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.text.Text

open class DraggableNode : BorderPane() {
    var x = 0.0
    var y = 0.0
    private val EPSILON = 1.0E-14 //util constant value for comparison

    private var mousex = 0.0 //for drag_and_drop
    private var mousey = 0.0 //for drag_and_drop
    private var dragging = false //for drag_and_drop
    protected var name: SimpleStringProperty = SimpleStringProperty()

    @FXML
    private lateinit var deleteButton : Button;

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("draggableNode.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
        this.prefWidth = 150.0
        this.prefHeight = 100.0

        val title = Text("")
        title.textProperty().bind(name)
        children.addAll(title)

        initialize()
    }

    open fun selfDestroy(it: MouseEvent) {
        (this.parent as Pane).children.remove(this)
    }

    private fun initialize() {
        deleteButton.onMouseClicked = EventHandler {
            selfDestroy(it)
        }
        onMousePressed = EventHandler {
            mousex = it.sceneX
            mousey = it.sceneY
        }

        onMouseDragged = EventHandler {
            val offsetX = it.sceneX - mousex
            val offsetY = it.sceneY - mousey

            x += offsetX
            y += offsetY

            layoutX = x
            layoutY = y

            dragging = true
            mousex = it.sceneX
            mousey = it.sceneY

            it.consume()
        }

        onMouseClicked = EventHandler {
            dragging = false
        }
    }
}
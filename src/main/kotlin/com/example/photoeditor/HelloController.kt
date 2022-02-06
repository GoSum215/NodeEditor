package com.example.photoeditor

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.control.ScrollPane
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import nu.pattern.OpenCV


class HelloController {
    @FXML
    lateinit var ImageViewContainer: ScrollPane
    @FXML
    lateinit var ImageViewObj: ImageView
    @FXML
    lateinit var DnDPane: Pane
    @FXML
    lateinit var layoutBase: VBox

    @FXML
    private lateinit var integerButton: Button
    @FXML
    private lateinit var exampleButton: Button

    fun initialize() {

        LinerSinglton.pane = DnDPane

        integerButton.onAction = EventHandler {
            val node = IntegerDraggableNode()
            node.prefWidth = 180.0
            node.prefHeight = 50.0

            DnDPane.children.addAll(node)
        }

        exampleButton.onAction = EventHandler {
            val node = ExampleDraggableNode()
            node.prefWidth = 180.0
            node.prefHeight = 50.0

            DnDPane.children.addAll(node)
        }
    }
}
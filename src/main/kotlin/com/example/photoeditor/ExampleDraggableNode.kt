package com.example.photoeditor

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.binding.Bindings
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.util.converter.NumberStringConverter

class ExampleDraggableNode() : DraggableNode() {
    var value: SimpleIntegerProperty = SimpleIntegerProperty()
    init {
        this.name.value = "Example"
    }

    val dCircle = DragCircle(value, false, this, SimpleIntegerProperty::class.java)
    val dCircleL = DragCircle(value, true, this, SimpleIntegerProperty::class.java)
    val button = Button("Selfdestroy")

    init {
        val textField = TextField()
//        textField.textProperty().addListener { _, _, newValue ->
//            //if (!newValue.matches(Regex("\\d*"))) {
//                textField.text = newValue.replace(Regex("[^\\d]"), "");
//            //}
//        }

        Bindings.bindBidirectional(textField.textProperty(), value, NumberStringConverter())

        this.name.value = "Example"
        (this.left as VBox).children.add(dCircleL)
        (this.right as VBox).children.add(dCircle)
    }



    override fun selfDestroy(it: MouseEvent) {
        LinerSinglton.deleteLine(dCircle)
        LinerSinglton.deleteLine(dCircleL)
        (parent as Pane).children.remove(this)
    }

    init {
        button.onMouseClicked = EventHandler{
            selfDestroy(it)
        }
    }
}

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

class IntegerDraggableNode() : DraggableNode() {
    var value: SimpleIntegerProperty = SimpleIntegerProperty()

    init {
        this.name.value = "Integer"
    }

    val dCircle = DragCircle(value, false, this, SimpleIntegerProperty::class.java)
    val button = Button("Selfdestroy")

    init {
        val textField = TextField()
//        textField.textProperty().addListener { _, _, newValue ->
//            if (!newValue.matches(Regex("\\d*"))) {
//                textField.text = newValue.replace(Regex("[^\\d]"), "");
//            }
//        }

        Bindings.bindBidirectional(textField.textProperty(), value, NumberStringConverter())

        this.name.value = "Integer"
        (this.right as VBox).children.add(dCircle)
    }

    override fun selfDestroy(it: MouseEvent) {
        LinerSinglton.deleteLine(dCircle)
        (parent as Pane).children.remove(this)
    }

    init {
        button.onMouseClicked = EventHandler{
            selfDestroy(it)
        }
    }
}

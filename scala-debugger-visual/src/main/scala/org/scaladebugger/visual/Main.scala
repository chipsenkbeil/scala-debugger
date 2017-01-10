package org.scaladebugger.visual

import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ToolBar}
import scalafx.scene.layout.{BorderPane, HBox}
import scalafx.stage.StageStyle

object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    initStyle(StageStyle.Undecorated)
    title = "Hello Stage"
    width = 600
    height = 450
    scene = new Scene {
      content = new BorderPane {
        style = "-fx-background-color: green;"
        top = new ToolBar {
          prefHeight = 25
          minHeight = 25
          maxHeight = 25
          items = new HBox {
            children = new Button {
              text = "X"
              onAction = e => Platform.exit()
            }
          }
        }
      }
    }
  }
}

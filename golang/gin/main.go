package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

//TIP <p>To run your code, right-click the code and select <b>Run</b>.</p> <p>Alternatively, click
// the <icon src="AllIcons.Actions.Execute"/> icon in the gutter and select the <b>Run</b> menu item from here.</p>

func main() {
	gin.DisableConsoleColor()
	router := gin.Default()
	router.Use(gin.Recovery())
	router.GET("/golang/gin/return-only-ok-code", getOkResponse)
	router.Run("localhost:8090")
}

func getOkResponse(c *gin.Context) {
	c.Status(http.StatusOK)
}

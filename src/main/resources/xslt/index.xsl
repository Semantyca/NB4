<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="utf-8" indent="no"/>

    <xsl:template match="/">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="layout">
        <xsl:text disable-output-escaping="yes">&lt;</xsl:text>!DOCTYPE html<xsl:text disable-output-escaping="yes">&gt;</xsl:text>
        <html lang="{//locale}">
            <head>
                <meta charset="UTF-8"/>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
                      integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous"/>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
                        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
                <title>Title</title>
            </head>
            <body>
                <div class="container pt-5" id="app">
                <div class="row mb-3">
                    <div class="col">
                        <h1>Cars application</h1>
                    </div>
                </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="search">Find your car</label>
                                <input type="text" class="form-control" id="search"/>
                            </div>

                        </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
                <script src="app.js"/>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

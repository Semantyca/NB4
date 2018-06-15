<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="utf-8" indent="no"/>

    <xsl:template match="/">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="layout">
        <xsl:text disable-output-escaping="yes">&lt;</xsl:text>!DOCTYPE html<xsl:text
            disable-output-escaping="yes">&gt;</xsl:text>
        <html>
            <head>
                <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css"/>
                <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
                <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
            </head>
            <body>
               <!-- <h3><xsl:value-of select="app"/></h3>-->
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <form class="form-horizontal" action="/nb/sessions" method="POST">
                                <fieldset>
                                    <div id="legend">
                                        <legend class="">Login</legend>
                                    </div>
                                    <div class="control-group">
                                        <!-- Username -->
                                        <label class="control-label"  for="userName">Username</label>
                                        <div class="controls">
                                            <input type="text" id="userName" identifier="userName" placeholder="" class="input-xlarge"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <!-- Password-->
                                        <label class="control-label" for="password">Password</label>
                                        <div class="controls">
                                            <input type="password" id="password" identifier="password" placeholder="" class="input-xlarge"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <!-- Button -->
                                        <div class="controls">
                                            <button class="btn btn-success">Login</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

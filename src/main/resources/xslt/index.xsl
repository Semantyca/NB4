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

            </head>
            <body>
                Index
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

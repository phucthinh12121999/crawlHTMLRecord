<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dataCrawlInfo.xsl
    Created on : July 4, 2020, 10:07 AM
    Author     : ADMIN
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:t="http://techeco.net/2017/xml"
                xmlns:xh="http://www.w3.org/1999/xhtml"
                xmlns="http://techeco.net/2017/xml"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="no" indent="yes"/>
     <xsl:template match="/">
        <item>
            <xsl:apply-templates/>
        </item>
    </xsl:template>
    <xsl:template match="xh:html/xh:body/xh:div[@id='main-page-content']">
        <info>
            <image>
                <xsl:apply-templates select="./xh:div[@id='product-wrap']/xh:div[contains(@class, 'product-item-root')]/xh:div[@id='product-gallery']/xh:div[@id='active-wrapper']/xh:div[@class='slide']/xh:div[@class='productImage']/@data-href"/>
<!--                <xsl:apply-templates select="./xh:div[@id='product-gallery']/xh:div[@id='active-wrapper']/xh:div[@class='slide']/xh:div[@class='productImage']/xh:img[@id='large-thumb']/@src"/>-->
                     <xsl:apply-templates select="text()"/>
            </image>
            <name>
                <xsl:apply-templates select="./xh:div[@id='product-wrap']/xh:div[contains(@class, 'product-item-root')]/xh:section[@id='product-info']/xh:div[contains(@class, 'product-header')]/xh:h1"/>
                     <xsl:apply-templates select="text()"/>
            </name>
<!--            <xsl:variable name="item_name" select="./form[contains(@action, '/cart/add')]/div[@class='info']/div[contains(@class, 'title')]"/>-->
            <type>
                <xsl:apply-templates select="./xh:div[contains(@class, 'yotpo')]/@data-type"/>
                     <xsl:apply-templates select="text()"/>
                            </type>
                            
<!--                <xsl:value-of select="$item_name"/>-->

            
<!--            <xsl:variable name="item_price" select="./form[contains(@action, '/cart/add')]/div[@class='info']/div[contains(@class, 'price')]/div[@class='deal']/span[@class='red-price']/span[@class='money']"/>-->
<!--                        <id>
                <xsl:apply-templates select="./xh:div[contains(@class, 'yotpo')]/@data-product-id"/>
                     <xsl:apply-templates select="text()"/>
                            </id>-->
                            <desc>
                <xsl:for-each select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li">
                                    <xsl:apply-templates select="text()"/>,
                </xsl:for-each>
<!--                <xsl:apply-templates select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li[1]"/>
                <xsl:apply-templates select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li[2]"/>
                <xsl:apply-templates select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li[3]"/>
                <xsl:apply-templates select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li[4]"/>
                <xsl:apply-templates select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li[5]"/>
                <xsl:apply-templates select="./xh:div[contains(@class, 'description')]/xh:div[contains(@class, 'group')]/xh:div[@class='group-body']/xh:ul/xh:li[6]"/>-->

            </desc>
            
        </info>    
    </xsl:template>
      <xsl:template match="text()">
    <xsl:value-of select="normalize-space()"/>
  </xsl:template>
</xsl:stylesheet>

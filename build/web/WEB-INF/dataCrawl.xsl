<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dataCrawl.xsl
    Created on : June 16, 2020, 1:58 PM
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
        <collections>
            <xsl:apply-templates/>
        </collections>
    </xsl:template>
    <xsl:template match="xh:html/xh:body/xh:div[@class='product-item flipimages-pending animation grid__item large--one-fifth medium--one-third small--one-half']">
        <xsl:attribute name="item_id">
            <xsl:value-of select="xh:html/xh:body/xh:div[@class='product-item flipimages-pending animation grid__item large--one-fifth medium--one-third small--one-half']"/>
        </xsl:attribute>
        <item id="{@id}" >
            <link>
                                <xsl:apply-templates select="./xh:form[@action='/cart/add']/xh:div[contains(@class, 'image-wrapper')]/xh:a[@class='product-link']/@href"/>
                     <xsl:apply-templates select="text()"/>
            </link>
<!--            <xsl:variable name="item_name" select="./form[contains(@action, '/cart/add')]/div[@class='info']/div[contains(@class, 'title')]"/>-->
            <name>
                <xsl:apply-templates select="./xh:form[@action='/cart/add']/xh:div[@class='info']/xh:div[contains(@class, 'title')]"/>
                     <xsl:apply-templates select="text()"/>
                            </name>
<!--                <xsl:value-of select="$item_name"/>-->

            
<!--            <xsl:variable name="item_price" select="./form[contains(@action, '/cart/add')]/div[@class='info']/div[contains(@class, 'price')]/div[@class='deal']/span[@class='red-price']/span[@class='money']"/>-->
            <price>
                <xsl:apply-templates select="./xh:form[@action='/cart/add']/xh:div[@class='info']/xh:div[contains(@class, 'price')]/xh:div[@class='deal']/xh:span[@class='red-price']/xh:span[@class='money']"/>
                <xsl:apply-templates select="text()"/>
<!--                <xsl:value-of select="$item_price"/>-->
            </price>
            
        </item>    
    </xsl:template>
      <xsl:template match="text()">
    <xsl:value-of select="normalize-space()"/>
  </xsl:template>
</xsl:stylesheet>

<!--<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:t="http://techeco.net/2017/xml"
                xmlns:xh="http://www.w3.org/1999/xhtml"
                xmlns="http://techeco.net/2017/xml"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="t:plants">
        <xsl:variable name="listDoc" select="document(@link)"/>
        <xsl:variable name="host" select="@host"/>
        <xsl:element name="plants">
            <xsl:for-each select="$listDoc//xh:div[@class='mw-category-group']//xh:li/xh:a">
                <xsl:variable name="pName" select="normalize-space(text())"/>
                <xsl:element name="plant">
                    <xsl:attribute name="name">
                        <xsl:value-of select="$pName"/>
                    </xsl:attribute>
                    <xsl:attribute name="link">
                        <xsl:value-of select="@href"/>
                    </xsl:attribute>
                    <xsl:variable name="detailDoc" select="document(concat($host, @href))"/>
                    <xsl:apply-templates select="$detailDoc//xh:div[@id='bodyContent']">
                        <xsl:with-param name="host" select="$host"/>
                    </xsl:apply-templates>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    <xsl:template name="aci_class_detail">
        <xsl:param name="eName"/>
        <xsl:param name="ele"/>
        <xsl:if test="boolean($ele)">
            <xsl:element name="eName">
                <xsl:value-of select="$ele/parent::xh:th/parent::xh:tr/xh:td//xh:e/@title|$ele/parent::xh|tr/xh:td//xh:b"/>
            </xsl:element>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="xh:table[@class='infobox taxobox']">
        <xsl:element name="aci_class">
            <xsl:variable name="v_regnum" select=".//xh:th/xh:a[@title='Giới (sinh học)']"/>
                        <xsl:variable name="v_phylum" select=".//xh:th/xh:a[@title='Ngành (sinh học)']"/>
            <xsl:element name="binominal_name">
                <xsl:variable name="binominal_label" select=".//xh:a[@title='Danh pháp hai phần']/parent::xh:th/parent::xh:tr"/>
                <xsl:value-of select="$binominal_label/following-sibling::xh:tr//xh:span/xh:i"/>
            </xsl:element>
            <xsl:call-template name="aci_class_detail">
                <xsl:with-param name="eName" select="'regmun'"/>
                <xsl:with-param name="ele" select="$v_regnum"/>
            </xsl:call-template>     
        </xsl:element>
    </xsl:template>
    <xsl:template match="xh:div[@id='bodyContent']">
        <xsl:param name="host"/>
        <xsl:apply-templates select=".//xh:table[@class='infobox taxobox']"/>
        <xsl:if test="boolean(.//xh:table[@class='infobox taxobox']//xh:tr[2]//xh:a/xh:img/@src)">
            <xsl:element name="avatar">
                <xsl:value-of select="$host"/>
                <xsl:value-of select=".//xh:table[@class='infobox taxobox']//xh:tr[2]//xh:a/xh:img/@src"/>
            </xsl:element>
            <xsl:variable name="toc" select=".//xh:div[@id='toc']//xh:span[contains(@class, 'toctext')]"/>
        </xsl:if>
    </xsl:template>
         TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    
    <xsl:template match="t:collections">
        <xsl:variable name="listDoc" select="document(@link)"/>
        <xsl:variable name="host" select="@host"/>
        <xsl:element name="collections">
            <xsl:for-each select="$listDoc//xh:div[contains(@class, 'product-item')]">
                <xsl:variable name="pName" select="normalize-space(text())"/>
                <xsl:element name="collection">
                    <xsl:attribute name="name">
                        <xsl:value-of select="$pName"/>
                    </xsl:attribute>
                    <xsl:attribute name="link">
                        <xsl:value-of select="@href"/>
                    </xsl:attribute>
                    <xsl:variable name="detailDoc" select="document(concat($host, @href))"/>
                    <xsl:apply-templates select="$detailDoc//xh:div[contains(@class, 'product-item-root')]">
                        <xsl:with-param name="host" select="$host"/>
                    </xsl:apply-templates>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    <xsl:template match="div[contains(@class, 'product-item-root')]">
        <xsl:element name="item_id">
            <xsl:variable name="name_product" select=".//section[@id='product-info']/div[contains(@class, 'product-header')]/h1[contains(@class, 'title')]"/>
                        <xsl:variable name="name_product" select=".//xh:section{@id='product-info'}/xh:div[@class='product-header']/xh:h1[@class='title']"/>
            <xsl:variable name="price_product" select=".//section[@id='product-info']/form[@id='product-actions']/div[contains(@class, 'price')]/div[contains(@class, 'deal')]/span[contains(@class, 'flow-price')]"/>
            <xsl:variable name="info_product" select=".//section[@id='product-info']/form[@id='product-actions']/div[contains(@class, 'info')]/div[contains(@itemprop, 'description')]/div[contains(@class, 'group')]/div[contains(@class, 'group-body')]/ul/li"/>
                        <xsl:call-template name="item_detail">
                <xsl:with-param name="eName" select="'name_product'"/>
                <xsl:with-param name="ele" select="$name_product"/>
            </xsl:call-template>
            <xsl:call-template name="item_detail">
                <xsl:with-param name="eName" select="'price_product'"/>
                <xsl:with-param name="ele" select="$price_product"/>
            </xsl:call-template>
            <xsl:call-template name="item_detail">
                <xsl:with-param name="eName" select="'info_product'"/>
                <xsl:with-param name="ele" select="$info_product"/>
            </xsl:call-template>
        </xsl:element>
    </xsl:template>
    
        <xsl:template match="xh:div{@id='product-gallery'}">
        <xsl:param name="it_image"/>
        <xsl:if test="boolean[$it_image]">
            <xsl:element name="images">
                <xsl:for-each select=""
            </xsl:element>
        </xsl:if>
    </xsl:template>
        
        
        <xsl:template name="item_detail">
        <xsl:param name="eName"/>
        <xsl:param name="ele"/>
        <xsl:if test="boolean($ele)">
            <xsl:if test="$eName='name_product'">
                <xsl:element name="eName">
                    <xsl:value-of select="$ele"/>
                </xsl:element>
            </xsl:if>
            <xsl:if test="$eName='price_product'">
                <xsl:element name="eName">
                    <xsl:value-of select="$ele"/>
                </xsl:element>
            </xsl:if>
            <xsl:if test="$eName='info_product'">
                <xsl:element name="eName">
                    <xsl:value-of select="$ele"/>
                </xsl:element>
            </xsl:if>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>-->

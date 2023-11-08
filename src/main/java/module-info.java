module com.cantillana {
    requires javafx.controls;
    requires java.xml;
    requires batik.dom;
    requires batik.svggen;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    exports com.cantillana.Lienzo;
    exports com.cantillana.app;
}

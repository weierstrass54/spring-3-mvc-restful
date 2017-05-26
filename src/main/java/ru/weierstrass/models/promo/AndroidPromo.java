package ru.weierstrass.models.promo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

public class AndroidPromo extends DetailPromo {

    private static final String CAPTION_SRC = "https://e2e4online.ru/shop/info/events/?id=";
    private static final String LINK_SRC = "";

    private static final List<String> attachments = Arrays.asList( "table", "img", "iframe" );
    private static final List<String> inlines = Arrays.asList( "font", "span" );

    public AndroidPromo( DetailPromo promo ) {
        id = promo.id;
        title = promo.title;
        image = promo.image;
        created = promo.created;
        contentShort = promo.contentShort;
        contentFull = promo.contentFull;
    }

    @Override
    public String getContentFull() {
        Element node = Jsoup.parseBodyFragment( super.getContentFull() ).getElementsByTag( "div" ).get( 0 );
        removeAttachments( node );
        removeSign( node );
        transformLinks( node );
        return node.outerHtml();
    }

    public String getCaption() {
        return CAPTION_SRC + getId();
    }

    private Element removeAttachments( Element node ) {
        node.getAllElements().forEach( element -> {
            if( attachments.contains( element.tagName() ) ) {
                element.remove();
            }
        } );
        return node;
    }

    private Element removeSign( Element node ) {
        boolean clear = false;
        for( Element element : node.getElementsByTag( "div" ) ) {
            if( !clear ) {
                if( element.getAllElements().size() == 0 ) {
                    continue;
                }
                Element subelement = element.getAllElements().get( 0 );
                if( inlines.contains( subelement.tagName() ) && subelement.text().contains( "_" ) ) {
                    clear = true;
                    subelement.remove();
                }
            }
            else {
                element.remove();
            }
        }
        return node;
    }

    private Element transformLinks( Element node ) {
        node.getElementsByTag( "a" ).forEach( element -> {
            String href = element.attr( "href" );
            if( href.startsWith( "/" ) ) {
                element.attr( "href", LINK_SRC + href );
            }
        } );
        return node;
    }

}

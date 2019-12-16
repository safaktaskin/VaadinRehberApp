package org.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@Theme("mytheme")
@Widgetset("org.example.MyAppWidgetset")
public class MyUI extends UI {

    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private VerticalLayout layout = new VerticalLayout();
    Table table = new Table();
    YeniKayitEkle yeniPopupWindow = new YeniKayitEkle();
    KisiGuncelle kisiGuncelle = new KisiGuncelle();
    KisiSil kisiSil = new KisiSil();
    KisiListele kisiListele = new KisiListele();

    TextField idTextField;
    TextField yeniAdTextField;
    TextField yeniSoyadTextField;
    TextField yeniSehirTextField;
    TextField yeniTelefonTextField;

    private Button delete;
    private Button update;
    private OperationButton save;
    private OperationButton listele;

    @Override
    public void init(VaadinRequest vaadinRequest) {

        BuildSaveButton();
        horizontalLayout.addComponents(save);

        BuildUpdateButton();
        horizontalLayout.addComponent(update);

        BuildDeleteButton();
        horizontalLayout.addComponent(delete);

        BuildListeleButton();
        horizontalLayout.addComponent(listele);

        layout.addComponent(horizontalLayout);
        layout.setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);

        setContent(layout);

        table.addContainerProperty("ID", Integer.class, null);
        table.addContainerProperty("AD", String.class, null);
        table.addContainerProperty("SOYAD", String.class, null);
        table.addContainerProperty("ŞEHİR", String.class, null);
        table.addContainerProperty("TELEFON", String.class, null);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Integer id = (Integer) itemClickEvent.getItem().getItemProperty("ID").getValue();
                idTextField.setValue(id.toString());
                String name = (String) itemClickEvent.getItem().getItemProperty("AD").getValue();
                yeniAdTextField.setValue(name);
                String surname= (String) itemClickEvent.getItem().getItemProperty("SOYAD").getValue();
                yeniSoyadTextField.setValue(surname);
                String city = (String) itemClickEvent.getItem().getItemProperty("ŞEHİR").getValue();
                yeniSehirTextField.setValue(city);
                String phone = (String) itemClickEvent.getItem().getItemProperty("TELEFON").getValue();
                yeniTelefonTextField.setValue(phone);
            }
        });

        kisiListele.tableListele(table);
        layout.addComponents(table);
        layout.setComponentAlignment(table, Alignment.TOP_CENTER);

    }

    private void BuildDeleteButton(){
        delete = new OperationButton("Sil");
        delete.setIcon(FontAwesome.TRASH);
        delete.addStyleName(ValoTheme.BUTTON_DANGER);
        delete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                MyUI.getCurrent().addWindow(kisiSil);
                table.refreshRowCache();
            }
        });
    }

    private void BuildUpdateButton(){
        update = new OperationButton("Güncelle");
        update.setIcon(FontAwesome.REFRESH);
        update.addStyleName(ValoTheme.BUTTON_PRIMARY);
        update.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                MyUI.getCurrent().addWindow(kisiGuncelle);
                table.refreshRowCache();
            }
        });
    }

    private void BuildSaveButton(){
        save = new OperationButton("Yeni Kayıt");
        save.setIcon(FontAwesome.SAVE);
        save.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                MyUI.getCurrent().addWindow(yeniPopupWindow);
            }
        });
    }

    private void BuildListeleButton(){
        listele = new OperationButton("Listele");
        listele.setIcon(FontAwesome.LIST);
        listele.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                kisiListele.tableListele(table);
            }
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

package org.example;

import com.vaadin.ui.Button;

/**
 * OperationButton
 *
 * @author Şafak Taşkın
 * @since 1.0.0
 */
public class OperationButton extends Button{

        public OperationButton() {
            setWidth(120, Unit.PIXELS);
        }

        public OperationButton(String caption) {
            this();
            setCaption(caption);
        }
}

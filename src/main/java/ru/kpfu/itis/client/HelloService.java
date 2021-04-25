package ru.kpfu.itis.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloService extends Service<String> {
    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    private final StringProperty name = new SimpleStringProperty(this, "name");

    public final StringProperty nameProperty() {
        return name;

    }
    public final void setName(String value) {
        name.set(value);

    }

    public final String getName() {
        return name.get();
    }

    @Override
    protected Task<String> createTask() {

        return new HelloTask(getName());
    }
}

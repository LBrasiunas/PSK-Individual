package com.psk.pskindividual.usecases;

import com.psk.pskindividual.interceptors.Log;
import com.psk.pskindividual.utils.RandomSentenceGenerator;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class ProduceRandomSentence implements Serializable {
    @Inject
    private RandomSentenceGenerator randomSentenceGenerator;

    private CompletableFuture<String> generatorTask = null;

    @Log
    public String generateMessage() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        generatorTask = CompletableFuture.supplyAsync(() -> randomSentenceGenerator.generateRandomSentence());

        return "/studentCourses.xhtml?faces-redirect=true&amp;universityId="
                + requestParameters.get("universityId")
                + "&amp;studentId=" + requestParameters.get("studentId");
    }

    public boolean isGeneratorTaskRunning() {
        return generatorTask != null && !generatorTask.isDone();
    }

    public String getGeneratorTaskStatus() throws ExecutionException, InterruptedException {
        if (generatorTask == null) {
            return null;
        }
        else if (isGeneratorTaskRunning()) {
            return "Random sentence is being generated right now.";
        }
        return "Random sentence: " + generatorTask.get();
    }
}
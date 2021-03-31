package u05lab;


import org.junit.jupiter.api.Test;
import scala.Option;
import scala.Some;
import u05lab.code.ExamResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class SomeJavaTest {

//    @Test
//    public void testExamResultsBasicBehaviour() {
//        // esame fallito, non c'è voto
//        assertEquals(ExamResult.failed().kind(), new ExamResult.Kind.FAILED());
//        assertFalse(ExamResult.failed().evaluation().isDefined());
//        assertFalse(ExamResult.failed().cumLaude());
//        assertEquals(ExamResult.failed().toString(), "FAILED");
//
//        // lo studente si è ritirato, non c'è voto
//        assertEquals(ExamResult.retired().kind(), new ExamResult.Kind.RETIRED());
//        assertFalse(ExamResult.retired().evaluation().isDefined());
//        assertFalse(ExamResult.retired().cumLaude());
//        assertEquals(ExamResult.retired().toString(), "RETIRED");
//
//        // 30L
//        assertEquals(ExamResult.succeededCumLaude().kind(), new ExamResult.Kind.SUCCEEDED());
//        assertEquals(ExamResult.succeededCumLaude().evaluation(), Optional.of(30));
//        assertTrue(ExamResult.succeededCumLaude().cumLaude());
//        assertEquals(ExamResult.succeededCumLaude().toString(), "SUCCEEDED(30L)");
//
//        // esame superato, ma non con lode
//        assertEquals(ExamResult.succeeded(28).kind(), new ExamResult.Kind.SUCCEEDED());
//        assertEquals(ExamResult.succeeded(28).evaluation(), Optional.of(28));
//        assertFalse(ExamResult.succeeded(28).cumLaude());
//        assertEquals(ExamResult.succeeded(28).toString(), "SUCCEEDED(28)");
//    }

    // metodo di creazione di una situazione di risultati in 3 appelli
//    private void prepareExams() {
//        em.createNewCall("gennaio");
//        em.createNewCall("febbraio");
//        em.createNewCall("marzo");
//
//        em.addStudentResult("gennaio", "rossi", ExamResult.failed()); // rossi -> fallito
//        em.addStudentResult("gennaio", "bianchi", ExamResult.retired()); // bianchi -> ritirato
//        em.addStudentResult("gennaio", "verdi", ExamResult.succeeded(28)); // verdi -> 28
//        em.addStudentResult("gennaio", "neri", ExamResult.succeededCumLaude()); // neri -> 30L
//
//        em.addStudentResult("febbraio", "rossi", ExamResult.failed()); // etc..
//        em.addStudentResult("febbraio", "bianchi", ExamResult.succeeded(20));
//        em.addStudentResult("febbraio", "verdi", ExamResult.succeeded(30));
//
//        em.addStudentResult("marzo", "rossi", ExamResult.succeeded(25));
//        em.addStudentResult("marzo", "bianchi", ExamResult.succeeded(25));
//        em.addStudentResult("marzo", "viola", ExamResult.failed());
//    }
//
//    // verifica base della parte obbligatoria di ExamManager
//    @org.junit.Test
//    public void testExamsManagement() {
//        this.prepareExams();
//        // partecipanti agli appelli di gennaio e marzo
//        assertEquals(em.getAllStudentsFromCall("gennaio"),new HashSet<>(Arrays.asList("rossi","bianchi","verdi","neri")));
//        assertEquals(em.getAllStudentsFromCall("marzo"),new HashSet<>(Arrays.asList("rossi","bianchi","viola")));
//
//        // promossi di gennaio con voto
//        assertEquals(em.getEvaluationsMapFromCall("gennaio").size(),2);
//        assertEquals(em.getEvaluationsMapFromCall("gennaio").get("verdi").intValue(),28);
//        assertEquals(em.getEvaluationsMapFromCall("gennaio").get("neri").intValue(),30);
//        // promossi di febbraio con voto
//        assertEquals(em.getEvaluationsMapFromCall("febbraio").size(),2);
//        assertEquals(em.getEvaluationsMapFromCall("febbraio").get("bianchi").intValue(),20);
//        assertEquals(em.getEvaluationsMapFromCall("febbraio").get("verdi").intValue(),30);
//
//        // tutti i risultati di rossi (attenzione ai toString!!)
//        assertEquals(em.getResultsMapFromStudent("rossi").size(),3);
//        assertEquals(em.getResultsMapFromStudent("rossi").get("gennaio"),"FAILED");
//        assertEquals(em.getResultsMapFromStudent("rossi").get("febbraio"),"FAILED");
//        assertEquals(em.getResultsMapFromStudent("rossi").get("marzo"),"SUCCEEDED(25)");
//        // tutti i risultati di bianchi
//        assertEquals(em.getResultsMapFromStudent("bianchi").size(),3);
//        assertEquals(em.getResultsMapFromStudent("bianchi").get("gennaio"),"RETIRED");
//        assertEquals(em.getResultsMapFromStudent("bianchi").get("febbraio"),"SUCCEEDED(20)");
//        assertEquals(em.getResultsMapFromStudent("bianchi").get("marzo"),"SUCCEEDED(25)");
//        // tutti i risultati di neri
//        assertEquals(em.getResultsMapFromStudent("neri").size(),1);
//        assertEquals(em.getResultsMapFromStudent("neri").get("gennaio"),"SUCCEEDED(30L)");
//
//    }
//
//    // verifica del metodo ExamManager.getBestResultFromStudent
//    @org.junit.Test
//    public void optionalTestExamsManagement() {
//        this.prepareExams();
//        // miglior voto acquisito da ogni studente, o vuoto..
//        assertEquals(em.getBestResultFromStudent("rossi"),Optional.of(25));
//        assertEquals(em.getBestResultFromStudent("bianchi"),Optional.of(25));
//        assertEquals(em.getBestResultFromStudent("neri"),Optional.of(30));
//        assertEquals(em.getBestResultFromStudent("viola"),Optional.empty());
//    }
//
//
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void optionalTestCantCreateACallTwice() {
//        this.prepareExams();
//        em.createNewCall("marzo");
//    }
//
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void optionalTestCantRegisterAnEvaluationTwice() {
//        this.prepareExams();
//        em.addStudentResult("gennaio", "verdi", ExamResult.failed());
//    }
}

package noraui.data.db;

import org.junit.Assert;
import org.junit.Test;

import noraui.exception.TechnicalException;

public class DBDataProviderUT {

    @Test
    public void testSqlSanitized4readOnlyWithSelect() {
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("select * from mydba where user='noraui'");
            Assert.assertTrue("Pas d'erreur car un Select est autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertFalse("Erreur car un Select est autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("Select * from mydba where user='noraui'");
            Assert.assertTrue("Pas d'erreur car un Select est autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertFalse("Erreur car un Select est autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("SELECT * FROM MYDBA WHERE USER='noraui'");
            Assert.assertTrue("Pas d'erreur car un Select est autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertFalse("Erreur car un Select est autorizée.", true);
        }
    }

    @Test
    public void testSqlSanitized4readOnlyWithDrop() {
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("drop table mydba");
            Assert.assertFalse("Erreur car un Drop n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Drop n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("Drop table mydba");
            Assert.assertFalse("Erreur car un Drop n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Drop n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("DROP TABLE MYDBA");
            Assert.assertFalse("Erreur car un Drop n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Drop n'est pas autorizée.", true);
        }
    }

    @Test
    public void testSqlSanitized4readOnlyWithDelete() {
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("delete table mydba");
            Assert.assertFalse("Erreur car un Delete n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Delete n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("Delete table mydba");
            Assert.assertFalse("Erreur car un Delete n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Delete n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("DELETE TABLE MYDBA");
            Assert.assertFalse("Erreur car un Delete n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Delete n'est pas autorizée.", true);
        }
    }

    @Test
    public void testSqlSanitized4readOnlyWithTruncate() {
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("truncate table 'mydba'");
            Assert.assertFalse("Erreur car un Truncate n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Truncate n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("Truncate Table 'mydba'");
            Assert.assertFalse("Erreur car un Truncate n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Truncate n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("TRUNCATE TABLE 'MYDBA'");
            Assert.assertFalse("Erreur car un Truncate n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Truncate n'est pas autorizée.", true);
        }
    }

    @Test
    public void testSqlSanitized4readOnlyWithUpdate() {
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("update mydba set user='noraui'");
            Assert.assertFalse("Erreur car un Update n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Update n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("Update mydba Set user='noraui'");
            Assert.assertFalse("Erreur car un Update n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Update n'est pas autorizée.", true);
        }
        try {
            noraui.data.db.DBDataProvider.sqlSanitized4readOnly("UPDATE MYDBA SET USER='noraui'");
            Assert.assertFalse("Erreur car un Update n'est pas autorizée.", true);
        } catch (TechnicalException e) {
            Assert.assertTrue("Pas d'erreur car un Update n'est pas autorizée.", true);
        }
    }

}

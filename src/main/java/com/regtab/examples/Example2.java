package com.regtab.examples;

import com.regtab.core.model.ITable;
import com.regtab.core.model.Recordset;
import com.regtab.core.readers.CSVReader;
import com.regtab.core.rtl.interpreter.RTLMatcher;
import com.regtab.core.rtl.interpreter.RTLPattern;
import com.regtab.core.rtl.interpreter.TableMatch;
import com.regtab.core.writers.CSVWriter;
import java.io.IOException;

public class Example2 {
    public static void main(String[] args) throws IOException {
        final String pathToSourceTable = args[0];
        final String pathToExtractedRecordset = args[1];

        // Create the reader to input the source table from CSV
        CSVReader csvReader = new CSVReader(pathToSourceTable);
        // Create the instance of ITM by reading data from the source table
        ITable itm = csvReader.read();

        // Present the RTL-pattern as a string
        String rtl = "[[SKIP][VAL: SCHEMA='MONTH']+]" +
                "[[(VAL: SCHEMA='INDICATOR' ',' VAL: SCHEMA='UOM')]" +
                "[(VAL: SCHEMA='MIN' '-' VAL: SCHEMA='MAX' '/' VAL: SCHEMA='AVE';" +
                "RECORD=(CELL;(ROW:C0);(COL:R0);'YEAR:2022';'LOC:Solzan';'SMPL:Sediment'))]+]+";

        // Compile the RTL-pattern
        RTLPattern pattern = RTLPattern.compile(rtl);
        assert pattern != null;

        // Create the matcher of the RTL-pattern
        RTLMatcher matcher = pattern.matcher();

        // Match the RTL-pattern with the instance of ITM
        TableMatch match = matcher.match(itm);

        // Apply the RTL-pattern to enrich the instance of ITM by semantics
        match.apply();

        // Inference the recordset from the instance of ITM
        Recordset recordset = itm.extract();

        // Store the extracted recordset to the CSV file
        CSVWriter csvWriter = new CSVWriter(pathToExtractedRecordset);
        csvWriter.write(recordset);
    }
}

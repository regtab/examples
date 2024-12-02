package com.regtab.examples;

import com.regtab.core.model.ITable;
import com.regtab.core.model.Recordset;
import com.regtab.core.readers.CSVReader;
import com.regtab.core.rtl.interpreter.RTLPattern;
import com.regtab.core.writers.CSVWriter;
import java.io.IOException;

public class Example1 {
    public static void main(String[] args) throws IOException {
        // Create the reader to input the source table from CSV
        CSVReader csvReader = new CSVReader("data/table1.csv");
        // Create the instance of ITM by reading data from the source table
        ITable itm = csvReader.read();

        // Present the RTL-pattern as a string
        String rtl = "[[ATTR][SKIP]{4}]" +
                "[[VAL: SCHEMA=COL; RECORD=ROW; JOIN=(UP:@TEXT==THIS@TEXT)]" +
                "{[ATTR][@BLANK ? SKIP | VAL: SCHEMA=LEFT]}{2}]+";

        // Apply the RTL-pattern to enrich the instance of ITM by semantics
        RTLPattern.apply(rtl, itm);

        // Inference the recordset from the instance of ITM
        Recordset recordset = itm.extract();

        // Store the extracted recordset to the CSV file
        CSVWriter csvWriter = new CSVWriter("data/recordset1.csv");
        csvWriter.write(recordset);
    }
}

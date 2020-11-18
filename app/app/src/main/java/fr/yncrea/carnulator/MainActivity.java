package fr.yncrea.carnulator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.yncrea.carnulator.api.CarnutesApiService;
import fr.yncrea.carnulator.database.BeersDatabase;
import fr.yncrea.carnulator.model.Beer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import fr.yncrea.carnulator.fragment.ContainerFragment;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {



    // PDF GENERATION UTILITIES //
    private static final int STORAGE_CODE = 1000;

    //Views
    Button mSaveBtn;

    // generated data
    Random random = new Random();
    int factureRef = 0;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date =  LocalDate.now();
    String today = dtf.format(date);
    String nextMonth = dtf.format(date.plusMonths(1));

    // API UTILITIES //
    private BeersDatabase db;
    public static List<Beer> beerList;
    private CarnutesApiService carnutesApiService;
    private Executor backgroundExecutor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // ********  BEGIN API PART ******** //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://carnulator-b911.restdb.io/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        carnutesApiService = retrofit.create(CarnutesApiService.class);

        //New thread pour la bdd
        backgroundExecutor.execute(()-> {
            db = Room.databaseBuilder(getApplicationContext(), BeersDatabase.class, "beers_database.db").build();
        });


        backgroundExecutor.execute(()-> {
            loadFromApiAndSave();
            beerList = db.BeersDao().getAllBeers();

            runOnUiThread(() -> {
                if(savedInstanceState == null){

                    setContentView(R.layout.activity_main);

                    getSupportFragmentManager().beginTransaction().add(R.id.containerCarnutes,new ContainerFragment()).commit();
                }
            });


        });




        // ********  END API PART ******** //


        // ********  BEGIN PDF GENERATION PART ******** //
        //LIRE ICI
        // ceci est une vue temporaire jsp où mettre le bouton !
        // bougez le ou vous voulez et appelez le "pdfButton"
        setContentView(R.layout.activity_main);
        //initializing button
        mSaveBtn = findViewById(R.id.exportButton);

        //handle button click
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // version
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                    // check storage permission
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                    }

                    else {
                        savePdf();

                    }
                }
                else {
                    savePdf();
                }

            }
        });
        // ********  END PDF GENERATION PART ******** //
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // ********  BEGIN PDF GENERATION PART ******** //
    private void savePdf(){
        factureRef = random.nextInt(999) + 1;
        String mFileName = "Document-" + factureRef;
        String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + mFileName + ".pdf";
        PdfWriter writer;

        // generating text
        try {
            writer = new PdfWriter(new FileOutputStream(mPath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // header
            Text mTitre = new Text("Malterie Brasserie des Carnutes")
                    .setFontSize(30).setBold();
            Text mSousTitre = new Text("37 rue des Montfort \n 45170 Neuville-aux-Bois")
                    .setFontSize(20);
            Text mFacture = new Text("Facture : " + factureRef)
                    .setFontSize(30);
            Text mDate = new Text("Date : " + today)
                    .setFontSize(20).setWidth(100);
            // LINE SEPARATOR
            LineSeparator lineSeparator = new LineSeparator(new DottedLine());
            lineSeparator.setStrokeColor(new DeviceRgb(0, 0, 68));

            // creating table
            float [] pointColumnWidths = {50F, 150F, 50F, 100F, 100F, 100F};
            Table table = new Table(pointColumnWidths);

            // creating cells
            table.addCell(new Cell().add("Réf").setBackgroundColor(Color.RED).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setBold());
            table.addCell(new Cell().add("Désignation").setBackgroundColor(Color.RED).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setBold());
            table.addCell(new Cell().add("Unité").setBackgroundColor(Color.RED).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setBold());
            table.addCell(new Cell().add("Quantité").setBackgroundColor(Color.RED).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setBold());
            table.addCell(new Cell().add("PU HT").setBackgroundColor(Color.RED).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setBold());
            table.addCell(new Cell().add("Total HT").setBackgroundColor(Color.RED).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setBold());
            table.setFontSize(15);

            //FONCTION REMPLISSAGE
            //fillBill();


            // creating bottom table
            float [] arrangedPointColumnWidths = {700F,300F};
            Table bottomTable = new Table(arrangedPointColumnWidths);
            // creating cells
            bottomTable.addCell(new Cell().add("Mode de régelment : Virement \n Echeance de paiement : " + nextMonth +"  \n Réglements :").setBorder(Border.NO_BORDER));
            bottomTable.addCell(new Cell().add("Total HT \nRéglements :\nNet à payer : ").setBorder(Border.NO_BORDER).setBackgroundColor(Color.RED).setFontColor(Color.WHITE));
            bottomTable.addCell(new Cell().add("\n\n\nTVA non applicable, article 293B du CGI").setBorder(Border.NO_BORDER));
            bottomTable.setFixedPosition(40,40,500).setFontSize(15);




            // adding content to document
            document.add(new Paragraph(mTitre));
            document.add(new Paragraph(mSousTitre));
            document.add(new Paragraph(mDate));

            // space with line
            document.add(new Paragraph(""));
            document.add(lineSeparator);
            document.add(new Paragraph(""));

            document.add(new Paragraph(mFacture));
            document.add(table);
            document.add(bottomTable);
            document.close();
            Toast.makeText(this, mFileName + ".pdf \n stocké dans \n" + mPath, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    // fill bill with data
    private void fillBill() {
    }

    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else {
                    Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // ********  END PDF GENERATION PART ******** //

    // ********  BEGIN API PART ******** //
    private void loadFromApiAndSave(){
        // Recup bières depuis API
        try {
            Response<List<Beer>> response = carnutesApiService.getBeers().execute();
            if(response.isSuccessful()){
                List<Beer> beers = response.body();
                Log.w("Carnutes APP","Bières: "+ beers.size());

                // Save beer bdd
                for(Beer beer : beers){
                    db.BeersDao().insert(beer);
                }
            }
            else{
                Log.w("Carnutes APP","Il y a eu un soucis avec la requête");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // ********  END API PART ******** //
}
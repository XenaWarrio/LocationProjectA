package dx.queen.a_app.code.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import dx.queen.a_app.code.model.db.GpsDao;
import dx.queen.a_app.code.model.db.GpsDatabase;
import dx.queen.a_app.code.model.db.OneDataBaseInstance;

public class WorkM extends Worker {

    @NonNull
    @Override
    public WorkerResult doWork() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("location");

        GpsDatabase database = OneDataBaseInstance.getInstance().getDatabase();
        GpsDao dao = database.gpsDao();


        for (int i = 0; i <= dao.getAll().size(); i++) {
            ref.setValue(dao.getAll().get(i));
            dao.delete(dao.getAll().get(i));
        }

        return WorkerResult.SUCCESS;
    }
}

package com.jobportal.Job.Portal.utility;

import com.jobportal.Job.Portal.entity.Sequence;
import com.jobportal.Job.Portal.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Utilities {
    private static MongoOperations mongoOperations;

    @Autowired
    public void setMongoOperations(MongoOperations operations){
        Utilities.mongoOperations=operations;
    }

    public static long generateSequence(String key) throws JobPortalException{
        Query query=new Query(Criteria.where("_id").is(key));
        Update update=new Update();
        update.inc("sequence",1);
        FindAndModifyOptions options=new FindAndModifyOptions();
        options.returnNew(true);
        Sequence s=mongoOperations.findAndModify(query,update,options,Sequence.class);
        if(s==null) throw new JobPortalException("Unable to generate the sequence");
        return s.getSequence();
    }
//    generating random otp for the password resetting
    public static String generateOtp(){
        StringBuilder stringBuilder=new StringBuilder();
        SecureRandom secureRandom=new SecureRandom();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(secureRandom.nextInt(10));
        }
        return stringBuilder.toString();
    }
}

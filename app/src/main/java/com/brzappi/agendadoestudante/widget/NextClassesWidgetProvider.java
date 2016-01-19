package com.brzappi.agendadoestudante.widget;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
 




import com.brzappi.agendadoestudante.NextClassesWidget;
import com.brzappi.agendadoestudante.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
 
public class NextClassesWidgetProvider extends AppWidgetProvider {
	DateFormat df = new SimpleDateFormat("hh:mm:ss");

	public static String CLOCK_WIDGET_UPDATE = "CLOCK_WIDGET_UPDATE";

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		if (CLOCK_WIDGET_UPDATE.equals(intent.getAction())) {
			Toast.makeText(context, "onReceiver()", Toast.LENGTH_LONG).show();
		}
	}

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;

		Log.i("ExampleWidget",	"Updating widgets " + Arrays.asList(appWidgetIds));

		// Perform this loop procedure for each App Widget that belongs to this
		// provider
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			// Create an Intent to launch ExampleActivity
			Intent intent = new Intent(context, NextClassesWidget.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,	intent, 0);

			// Get the layout for the App Widget and attach an on-click listener
			// to the button
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_nextclasseshome);
			views.setOnClickPendingIntent(R.id.button, pendingIntent);

			// To update a label
			views.setTextViewText(R.id.widget1label, df.format(new Date()));

			// Tell the AppWidgetManager to perform an update on the current app
			// widget
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	public static void updateAppWidget(Context context,	AppWidgetManager appWidgetManager, int appWidgetId) {
		String currentTime ="18-17-2014";
		String strWidgetText = currentTime;

		RemoteViews updateViews = new RemoteViews(context.getPackageName(),	R.layout.widget_nextclasseshome);
		updateViews.setTextViewText(R.id.widget1label, "[" + String.valueOf(appWidgetId) + "]" + strWidgetText);
		appWidgetManager.updateAppWidget(appWidgetId, updateViews);

		Toast.makeText(context,	"updateAppWidget(): " + String.valueOf(appWidgetId) + "\n"+ strWidgetText, Toast.LENGTH_LONG).show();

	}
}
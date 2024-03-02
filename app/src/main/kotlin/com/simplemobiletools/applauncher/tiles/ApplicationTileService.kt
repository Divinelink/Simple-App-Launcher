package com.simplemobiletools.applauncher.tiles

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
@Suppress("DEPRECATION")
@SuppressLint("StartActivityAndCollapseDeprecated")
class ApplicationTileService : TileService() {

    override fun onClick() {
        super.onClick()

        qsTile.state = Tile.STATE_ACTIVE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
            val pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, PendingIntent.FLAG_IMMUTABLE)
            startActivityAndCollapse(pendingIntent)
        } else {
            val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
            startActivityAndCollapse(launchIntent)
        }

        qsTile.updateTile()
    }
}

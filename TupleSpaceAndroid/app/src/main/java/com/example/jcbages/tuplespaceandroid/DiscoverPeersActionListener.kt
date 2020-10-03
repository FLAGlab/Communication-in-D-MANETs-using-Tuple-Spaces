package com.example.jcbages.tuplespaceandroid

import android.net.wifi.p2p.WifiP2pManager
import android.util.Log

class DiscoverPeersActionListener: WifiP2pManager.ActionListener {

    private val TAG = "DiscoverPeersActionListener"

    override fun onSuccess() {
        Log.v(TAG, "discoverPeers onSuccess")
    }

    override fun onFailure(reason: Int) {
        Log.v(TAG, "discoverPeers onFailure for reason" + reason.toString())
    }

}
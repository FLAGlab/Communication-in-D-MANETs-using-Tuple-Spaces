package com.example.jcbages.tuplespaceandroid

import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val intentFilter = IntentFilter()

    private lateinit var mChannel: WifiP2pManager.Channel
    private lateinit var mManager: WifiP2pManager

    private lateinit var receiver: WiFiDirectBroadcastReceiver

    private val peers = mutableListOf<WifiP2pDevice>()

    var isWifiP2pEnabled: Boolean = false

    private val peerListListener = WifiP2pManager.PeerListListener { peerList ->
        val refreshedPeers = peerList.deviceList
        if (refreshedPeers != peers) {
            peers.clear()
            peers.addAll(refreshedPeers)


            var message = "Listing peers...\n"
            for (peer in peers) {
                message += " * " + peer.deviceName + "\n"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        if (peers.isEmpty()) {
            Toast.makeText(this, "No device found", Toast.LENGTH_SHORT).show()
            return@PeerListListener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)

        // This channel object will be used later to connect to the Wi-Fi P2P framework
        mManager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        mChannel = mManager.initialize(this, mainLooper, null)

        // Discover available peers
        val discoverPeersActionListener = DiscoverPeersActionListener()
        mManager.discoverPeers(mChannel, discoverPeersActionListener)
    }

    public override fun onResume() {
        super.onResume()
        receiver = WiFiDirectBroadcastReceiver(mManager, mChannel, this, peerListListener)
        registerReceiver(receiver, intentFilter)
    }

    public override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}

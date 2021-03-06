/*
 *   Copyright © 2017 Teclib. All rights reserved.
 *
 * This file is part of flyve-mdm-android-agent
 *
 * flyve-mdm-android-agent is a subproject of Flyve MDM. Flyve MDM is a mobile
 * device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @date      02/06/2017
 * @copyright Copyright © 2017 Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android-agent
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */

package org.flyve.mdm.agent.data;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStorage {

	private static final String SHARED_PREFS_FILE = "FlyveHMPrefs";
	private Context mContext;

	/**
	 * Constructor 
	 * @param context
	 */
	public DataStorage(Context context){
		mContext = context;
	}

	/**
	 * Get preference from setting
	 * @return SharedPreferences
	 */
	private SharedPreferences getSettings(){
		if (mContext != null) {
		return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
		} else {
			return null;
		}
	}

	public boolean getOnlineStatus() {
		return Boolean.valueOf(getData("onlineStatus"));
	}
	public void setOnlineStatus(boolean status) {
		setData("onlineStatus", String.valueOf(status));
	}

	public String getManifestVersion() {
		return getData("manifestVersion");
	}
	public void setManifestVersion(String version) {
		setData("manifestVersion", version);
	}

	public String getUrl() {
		return getData("url");
	}

	public void setUrl(String url) {
		setData("url", url);
	}

	public String getUserToken() {
		return getData("user_token");
	}

	public void setUserToken(String userToken) {
		setData("user_token", userToken);
	}

	public String getInvitationToken() {
		return getData("invitation_token");
	}

	public void setInvitationToken(String invitationToken) {
		setData("invitation_token", invitationToken);
	}

	public String getSessionToken() {
		return getData("session_token");
	}

	public void setSessionToken(String sessionToken) {
		setData("session_token", sessionToken);
	}

	public String getProfileId() {
		return getData("profile_id");
	}

	public void setProfileId(String profileId) {
		setData("profile_id", profileId);
	}

	public String getAgentId() {
		return getData("agent_id");
	}

	public void setAgentId(String agentId) {
		setData("agent_id", agentId);
	}

	public String getBroker() {
		return getData("broker");
	}

	public void setBroker(String broker) {
		setData("broker", broker);
	}

	public String getPort() {
		return getData("port");
	}

	public void setPort(String port) {
		setData("port", port);
	}

	public String getTls() {
		return getData("tls");
	}

	public void setTls(String tls) {
		setData("tls", tls);
	}

	public String getTopic() {
		return getData("topic");
	}

	public void setTopic(String topic) {
		setData("topic", topic);
	}

	public String getMqttuser() {
		return getData("mqttuser");
	}

	public void setMqttuser(String mqttuser) {
		setData("mqttuser", mqttuser);
	}

	public String getMqttpasswd() {
		return getData("mqttpasswd");
	}

	public void setMqttpasswd(String mqttpasswd) {
		setData("mqttpasswd", mqttpasswd);
	}

	public String getCertificate() {
		return getData("certificate");
	}

	public void setCertificate(String certificate) {
		setData("certificate", certificate);
	}

	public String getName() {
		return getData("name");
	}

	public void setName(String name) {
		setData("name", name);
	}

	public String getComputersId() {
		return getData("computers_id");
	}

	public void setComputersId(String computersId) {
		setData("computers_id", computersId);
	}

	public String getEntitiesId() {
		return getData("entities_id");
	}

	public void setEntitiesId(String entitiesId) {
		setData("entities_id", entitiesId);
	}

	public String getPluginFlyvemdmFleetsId() {
		return getData("plugin_flyvemdm_fleets_id");
	}

	public void setPluginFlyvemdmFleetsId(String pluginFlyvemdmFleetsId) {
		setData("plugin_flyvemdm_fleets_id", pluginFlyvemdmFleetsId);
	}

	public void setConnectivityWifiDisable(boolean disable) {
		setData("ConnectivityWifiDisable", String.valueOf(disable));
	}

	public boolean getConnectivityWifiDisable() {
		return Boolean.valueOf(getData("ConnectivityWifiDisable"));
	}

	public void setConnectivityBluetoothDisable(boolean disable) {
		setData("ConnectivityBluetoothDisable", String.valueOf(disable));
	}

	public boolean getConnectivityBluetoothDisable() {
		return Boolean.valueOf(getData("ConnectivityBluetoothDisable"));
	}

	public void setConnectivityGPSDisable(boolean disable) {
		setData("ConnectivityGPSDisable", String.valueOf(disable));
	}

	public boolean getConnectivityGPSDisable() {
		return Boolean.valueOf(getData("ConnectivityGPSDisable"));
	}

	public void setEasterEgg(boolean enable) {
		setData("easterEgg", String.valueOf(enable));
	}

	public boolean getEasterEgg() {
		return Boolean.valueOf(getData("easterEgg"));
	}

	private String getData(String key){
		String data = "";
		SharedPreferences sp = getSettings();
		if(sp != null) {
			data = sp.getString(key, null);
		}
		return data;
	}

	private void setData(String key, String value) {
		SharedPreferences sp = getSettings();
		if(sp != null) {
			SharedPreferences.Editor editor = sp.edit();
			editor.putString(key, value );
			editor.apply();
		}
	}

	public void clearSettings(){
		SharedPreferences sp = getSettings();
		if(sp != null) {
			SharedPreferences.Editor editor = sp.edit();
			editor.clear();
			editor.apply();
		}
	}

	public void deleteKeyCache(String llave){
		SharedPreferences sp = getSettings();
		if(sp != null) {
			SharedPreferences.Editor editor = sp.edit();
			editor.remove(llave);
			editor.apply();
		}
	}
}



import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class AlarmView extends StatefulWidget {
  @override
  _AlarmViewState createState() => _AlarmViewState();
}

class _AlarmViewState extends State<AlarmView> {
  static const platform = MethodChannel('samples.flutter.dev/battery');
  TimeOfDay ?_time;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _time = TimeOfDay.now();
  }

  String _alarmStatus = 'Alarm is not set';

  Future<void> setAlarm(TimeOfDay time)async {

    var realtime = DateTime.now();

    var hour = realtime.hour -  time.hour;
    var min = (realtime.minute- time.minute).abs();
    var second = realtime.second ;
    print(hour);
    print(hour);
    print(min);
    print(-second);
    String total_time_alarm_trigger = realtime.add(Duration(hours: hour,minutes: min,seconds: -second,milliseconds: 0)).millisecondsSinceEpoch.toString();
    // String total_time_alarm_trigger = new DateTime.now().millisecondsSinceEpoch.toString();
    var params= <String,dynamic>{
      "paramsfromflutter" : total_time_alarm_trigger
    };


    String alarmStatus;
    try {
      final String result = await platform.invokeMethod('getAlarm',params);
      alarmStatus = result;
    } on PlatformException catch (e) {
      alarmStatus = "Failed to get alarm status : '${e.message}'.";
    }

    setState(() {
      _alarmStatus = alarmStatus;
    });



  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              child: Text("${_time!.hour} : ${_time!.minute}" ),


            ),
            Container(
              child: Text(_alarmStatus),
            ),

            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(onPressed: (){_timepicker();

                }, child: Text("Set Time")),
                ElevatedButton(onPressed: (){
                  setAlarm(_time!);
                },child: Text("Set Alarm"),)

              ],
            ),


          ],
        ),
      ),
    );
  }

  _timepicker() async{
    TimeOfDay? time = await showTimePicker(context: context,initialTime: _time!,);
      if(time != null){
        setState(() {
          _time=time;
        });
      }
}


}
